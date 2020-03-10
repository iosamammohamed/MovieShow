package com.osama.movieshow.data.movie

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.osama.movieshow.utils.Urls
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object MovieApiClient {

    val retrofit:Retrofit
    val movieApiInterface:MovieApiInterface
    val okHttpClient: OkHttpClient

    init{
        okHttpClient = OkHttpClient.Builder().connectTimeout(15,TimeUnit.SECONDS).build()
        retrofit = Retrofit.Builder().baseUrl(Urls.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
        movieApiInterface = retrofit.create(MovieApiInterface::class.java)
    }



    fun getMovies(url:String, callback: MovieCallback){
        movieApiInterface.getMovies(url).enqueue(object: Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d("hhhhhhhhhhh ", call.request().url().toString())
                val jsonArray =
                    response.body()!!.getAsJsonArray("results")
                val moviesList: List<Movie> = Gson().fromJson(
                    jsonArray.toString(),
                    object : TypeToken<List<Movie?>?>() {}.type
                )
                println("lllllllllllllllloooooooooooo success")
                callback.onSuccess(moviesList)
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println("lllllllllllllllloooooooooooo failed")
                callback.onFailure(t.message.toString())
            }
        })
    }


}