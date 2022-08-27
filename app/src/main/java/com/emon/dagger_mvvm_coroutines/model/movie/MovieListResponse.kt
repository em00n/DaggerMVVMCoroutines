package com.emon.dagger_mvvm_coroutines.model.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("results")
    @Expose
    val movieInfo: List<MovieInfo>,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int,

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int
)
