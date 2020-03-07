package com.osama.movieshow.data.movie

import com.google.gson.JsonObject
import com.osama.movieshow.utils.Urls
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiInterface {

    @GET("${Urls.popular}${Urls.apiKey}")
    fun getPopularMovies(): Call<JsonObject>

    @GET("${Urls.nowPlaying}${Urls.apiKey}")
    fun getLatestMovies(): Call<JsonObject>

    @GET("${Urls.topRated}${Urls.apiKey}")
    fun getTopRatedMovies(): Call<JsonObject>

    @GET("${Urls.upComing}${Urls.apiKey}")
    fun getUpcomingMovies(): Call<JsonObject>

    @GET("{url}${Urls.apiKey}")
    fun getMovies(@Path("url", encoded = true) url:String): Call<JsonObject>

}