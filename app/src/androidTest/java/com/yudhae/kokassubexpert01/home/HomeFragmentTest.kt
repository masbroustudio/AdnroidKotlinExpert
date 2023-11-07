//package com.yudhae.kokassubexpert01.home
//
//import com.kokassubexpert01.core.data.KokasRepository
//import com.yudhae.kokassubexpert01.core.di.*
//import com.kokassubexpert01.core.domain.model.Kokas
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.koin.core.context.loadKoinModules
//import org.koin.core.context.stopKoin
//import org.koin.test.KoinTest
//import org.koin.test.inject
//import kotlin.test.assertNotNull
//
//class HomeFragmentTest : KoinTest {
//    private val repository: KokasRepository by inject()
//    private val dummyData = Kokas(
//        tourismId = "-",
//        name = "gemah",
//        description = "-",
//        address = "tulungagung",
//        latitude = 0.0,
//        longitude = 0.0,
//        like = 1,
//        image = "-",
//        isFavorite = false,
//    )
//
//        @Before
//        fun setup() {
//            loadKoinModules(
//                listOf(
//                    databaseModule,
//                    networkModule,
//                    repositoryModule,
//                    useCaseModule,
//                    viewModelModule,
//                )
//            )
//        }
//
//        @After
//        fun tearDown() {
//            stopKoin()
//        }
//
//        @Test
//        fun check_data_from_repository_is_not_null() {
//            val response = repository.getFavoriteTourism()
//            println(repository.getFavoriteTourism())
//
//            assertNotNull(response)
//        }
//}