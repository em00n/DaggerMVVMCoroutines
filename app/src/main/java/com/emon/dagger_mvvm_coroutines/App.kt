package com.emon.dagger_mvvm_coroutines

import android.app.Application
import com.emon.dagger_mvvm_coroutines.di.ApplicationComponent
import com.emon.dagger_mvvm_coroutines.di.DaggerApplicationComponent
import com.emon.dagger_mvvm_coroutines.di.module.NetworkModule
import com.emon.dagger_mvvm_coroutines.di.module.ApplicationModule

class App : Application() {
    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .applicationModule(ApplicationModule(this))
            .build()
    }
    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}
