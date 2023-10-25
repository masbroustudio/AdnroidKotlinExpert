menambahkan library-library yang dibutuhkan terlebih dahulu di build.gradle(Module: app) 
```
dependencies {
    ...
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("androidx.activity:activity-ktx:1.6.1") //by viewModels()
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1") //lifecycleScope
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1") //asLiveData
}
```