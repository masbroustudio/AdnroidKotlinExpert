package com.yudhae.kokasexpert

import android.app.Application
import com.yudhae.kokasexpert.core.di.databaseModule
import com.yudhae.kokasexpert.core.di.networkModule
import com.yudhae.kokasexpert.core.di.repositoryModule
import com.yudhae.kokasexpert.di.useCaseModule
import com.yudhae.kokasexpert.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}