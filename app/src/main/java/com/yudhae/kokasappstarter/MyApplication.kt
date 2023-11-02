package com.yudhae.kokasappstarter

import android.app.Application
import com.yudhae.kokasappstarter.core.di.databaseModule
import com.yudhae.kokasappstarter.core.di.networkModule
import com.yudhae.kokasappstarter.core.di.repositoryModule
import com.yudhae.kokasappstarter.di.useCaseModule
import com.yudhae.kokasappstarter.di.viewModelModule

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