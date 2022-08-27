package com.emon.dagger_mvvm_coroutines.di

import com.emon.dagger_mvvm_coroutines.di.module.ApplicationModule
import com.emon.dagger_mvvm_coroutines.di.module.NetworkModule
import com.emon.dagger_mvvm_coroutines.di.module.ViewModelModule
import com.emon.dagger_mvvm_coroutines.ui.movie_list.MovieListFragment
import com.emon.dagger_mvvm_coroutines.ui.moviedetails.MovieDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,ApplicationModule::class,ViewModelModule::class])
interface ApplicationComponent {
    fun inject(movieListFragment: MovieListFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}