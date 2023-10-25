# Tourism App (Retrofit)
Pertama masukkan terlebih dahulu beberapa library build.gradle (Project)
```
    buildscript {
   ...
   ext.rxjava_version = "2.2.19"
   ext.rxandroid_version = "2.1.1"
   ext.lifecycle_version = "2.2.0"
   ...
    }
```

Pada build.gradle (Module: app)
```
    dependencies {
   ...
   implementation "io.reactivex.rxjava2:rxjava:$rxjava_version"
   implementation "io.reactivex.rxjava2:rxandroid:$rxandroid_version"
   implementation "com.squareup.retrofit2:adapter-rxjava2:$retrofit_version"
   implementation "androidx.room:room-rxjava2:$room_version"
   implementation "androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version"
    }
```

menambahkan RxJava2CallAdapterFactory pada ApiConfig seperti berikut:
```
    val retrofit = Retrofit.Builder()
               .baseUrl("https://tourism-api.dicoding.dev/")
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .client(provideOkHttpClient())
               .build()
    return retrofit.create(ApiService::class.java) 
```

Kemudian ubah tipe data pada ApiService menjadi Flowable
```
    interface ApiService {
   @GET("list")
   fun getList(): Flowable<ListTourismResponse>
}
```

saatnya mengubah kode pada RemoteDataSource agar bisa menghasilkan Flowable seperti berikut:
```
    fun getAllTourism(): Flowable<ApiResponse<List<TourismResponse>>> {
    val resultData = PublishSubject.create<ApiResponse<List<TourismResponse>>>()
 
    // Get data from remote api
    val client = apiService.getList()
 
    client
       .subscribeOn(Schedulers.computation())
       .observeOn(AndroidSchedulers.mainThread())
       .take(1)
       .subscribe ({ response ->
           val dataArray = response.places
           resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
        }, { error ->
           resultData.onNext(ApiResponse.Error(error.message.toString()))
           Log.e("RemoteDataSource", error.toString())
        })
 
    return resultData.toFlowable(BackpressureStrategy.BUFFER)
}
```

Dari remote data source, beralih ke local data source. Ubah nilai kembalian pada TourismDao menjadi seperti berikut:
```
    @Dao
    interface TourismDao {
     
       @Query("SELECT * FROM tourism")
       fun getAllTourism(): Flowable<List<TourismEntity>>
     
       @Query("SELECT * FROM tourism where isFavorite = 1")
       fun getFavoriteTourism(): Flowable<List<TourismEntity>>
     
       @Insert(onConflict = OnConflictStrategy.REPLACE)
       fun insertTourism(tourism: List<TourismEntity>): Completable
     
       @Update
       fun updateFavoriteTourism(tourism: TourismEntity)
    }
```

Kemudian masuk ke LocalDataSource dan ubah juga tipe kembalian dari masing-masing fungsi seperti ini:
```
    class LocalDataSource private constructor(private val tourismDao: TourismDao) {
     
       ...
     
       fun getAllTourism(): Flowable<List<TourismEntity>> = tourismDao.getAllTourism()
     
       fun getFavoriteTourism(): Flowable<List<TourismEntity>> = tourismDao.getFavoriteTourism()
     
       fun insertTourism(tourismList: List<TourismEntity>) = tourismDao.insertTourism(tourismList)
     
       fun setFavoriteTourism(tourism: TourismEntity, newState: Boolean) {
           tourism.isFavorite = newState
           tourismDao.updateFavoriteTourism(tourism)
       }
    }
```

sesuaikan kode pada NetworkBoundResource sehingga menjadi seperti ini:
```
    abstract class NetworkBoundResource<ResultType, RequestType> {
 
       private val result = PublishSubject.create<Resource<ResultType>>()
       private val mCompositeDisposable = CompositeDisposable()
     
       init {
           @Suppress("LeakingThis")
           val dbSource = loadFromDB()
           val db = dbSource
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .take(1)
               .subscribe { value ->
                   dbSource.unsubscribeOn(Schedulers.io())
                   if (shouldFetch(value)) {
                       fetchFromNetwork()
                   } else {
                       result.onNext(Resource.Success(value))
                   }
               }
           mCompositeDisposable.add(db)
       }
       
       ...
       
       private fun fetchFromNetwork() {
       val apiResponse = createCall()
       result.onNext(Resource.Loading(null))
       val response = apiResponse
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .take(1)
           .doOnComplete {
               mCompositeDisposable.dispose()
           }
           .subscribe{ response ->
               when(response){
                   is ApiResponse.Success -> {
                       saveCallResult(response.data)
                       val dbSource = loadFromDB()
                       dbSource.subscribeOn(Schedulers.computation())
                           .observeOn(AndroidSchedulers.mainThread())
                           .take(1)
                           .subscribe {
                               dbSource.unsubscribeOn(Schedulers.io())
                               result.onNext(Resource.Success(it))
                           }
                   }
                   is ApiResponse.Empty -> {
                       val dbSource = loadFromDB()
                       dbSource.subscribeOn(Schedulers.computation())
                           .observeOn(AndroidSchedulers.mainThread())
                           .take(1)
                           .subscribe {
                               dbSource.unsubscribeOn(Schedulers.io())
                               result.onNext(Resource.Success(it))
                           }
                   }
                   is ApiResponse.Error -> {
                       onFetchFailed()
                       result.onNext(Resource.Error(response.errorMessage, null))
                   }
               }
           }
       mCompositeDisposable.add(response)
   }
   fun asFlowable(): Flowable<Resource<ResultType>> =
       result.toFlowable(BackpressureStrategy.BUFFER)
}
```

Karena hasil dari NetworkBoundResource berbeda, yaitu Flowable. MakaAnda juga perlu mengganti kode pada Repository. Mulai dengan mengubah kode pada ITourismRepository.
```
    interface ITourismRepository {
 
       fun getAllTourism(): Flowable<Resource<List<Tourism>>>
     
       fun getFavoriteTourism(): Flowable<List<Tourism>>
     
       fun setFavoriteTourism(tourism: Tourism, state: Boolean)
     
    }
```

Selanjutnya sesuaikan juga pada TourismRepository seperti ini:
```
    TODO : 8
```

Saatnya masuk ke layer use case. Ubahlah kode pada TourismUseCase seperti berikut:
```
    interface TourismUseCase {
        fun getAllTourism(): Flowable<Resource<List<Tourism>>>
        fun getFavoriteTourism(): Flowable<List<Tourism>>
        fun setFavoriteTourism(tourism: Tourism, state: Boolean)
    }
```

Dan terakhir pada HomeViewModel
```
class HomeViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
   val tourism = LiveDataReactiveStreams.fromPublisher(tourismUseCase.getAllTourism())
}
```

FavoriteViewModel
```
class FavoriteViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val favoriteTourism = LiveDataReactiveStreams.fromPublisher(tourismUseCase.getFavoriteTourism())
}
```