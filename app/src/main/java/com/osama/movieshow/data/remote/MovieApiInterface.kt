package com.osama.movieshow.data.remote

import com.google.gson.JsonObject
import com.osama.movieshow.data.model.movie.MovieResponse
import com.osama.movieshow.utils.Constants
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiInterface {

    @GET("${Constants.popular}${Constants.apiKey}")
    fun getPopularMovies(): Call<JsonObject>

    @GET("${Constants.nowPlaying}${Constants.apiKey}")
    fun getLatestMovies(): Call<JsonObject>

    @GET("${Constants.topRated}${Constants.apiKey}")
    fun getTopRatedMovies(): Call<JsonObject>

    @GET("${Constants.upComing}${Constants.apiKey}")
    fun getUpcomingMovies(): Call<JsonObject>

    @GET("{url}${Constants.apiKey}")
    fun getMovies(@Path("url", encoded = true) url:String): Single<MovieResponse>

}