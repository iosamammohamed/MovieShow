package com.osama.movieshow.data.movie

import com.google.gson.JsonObject
import com.osama.movieshow.utils.Urls
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MovieApiClient {

    val retrofit:Retrofit
    val movieApiInterface:MovieApiInterface

    init{
        retrofit = Retrofit.Builder().baseUrl(Urls.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        movieApiInterface = retrofit.create(MovieApiInterface::class.java)
    }

    fun getPopularMovies(): Call<JsonObject>{
        return this.movieApiInterface.getPopularMovies()
    }

    fun getLatestMovies(): Call<JsonObject>{
        return this.movieApiInterface.getLatestMovies()

    }

    fun getUpcomingMovies():Call<JsonObject>{
        return this.movieApiInterface.getUpcomingMovies()

    }

    fun getTopRatedMovies():  Call<JsonObject>{
        return this.movieApiInterface.getTopRatedMovies()

    }


}