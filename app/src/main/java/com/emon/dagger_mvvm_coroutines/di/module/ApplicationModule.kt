package com.emon.dagger_mvvm_coroutines.di.module

import android.content.Context
import com.emon.dagger_mvvm_coroutines.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val app: App) {

    @Provides
    @Singleton
    fun provideApp(): App = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext
}