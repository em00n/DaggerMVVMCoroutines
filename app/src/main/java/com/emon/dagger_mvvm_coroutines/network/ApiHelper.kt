package com.emon.dagger_mvvm_coroutines.network

import com.emon.dagger_mvvm_coroutines.model.movie.MovieInfo
import retrofit2.Response

interface ApiHelper {

    suspend fun getMovieDetails(id: Int, apiKey: String): Response<MovieInfo>

}