package com.emon.dagger_mvvm_coroutines.di.module

import androidx.lifecycle.ViewModel
import com.emon.dagger_mvvm_coroutines.viewmodel.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ClassKey(MovieViewModel::class)
    @IntoMap
    abstract fun movieViewModel(movieViewModel: MovieViewModel) : ViewModel

}