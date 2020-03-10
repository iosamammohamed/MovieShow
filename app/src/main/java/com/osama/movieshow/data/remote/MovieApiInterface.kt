package com.osama.movieshow.data.remote

import com.osama.movieshow.data.model.movie.MovieResponse
import com.osama.movieshow.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiInterface {

    @GET("{url}${Constants.apiKey}")
    fun getMovies(@Path("url", encoded = true) url:String): Single<MovieResponse>

}