package com.osama.movieshow.data.remote

import com.osama.movieshow.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    private val retrofit:Retrofit
    private val movieApiInterface: MovieApiInterface
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().connectTimeout(15,TimeUnit.SECONDS).build()

    init{
        retrofit = Retrofit.Builder().baseUrl(Constants.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
        movieApiInterface = retrofit.create(
            MovieApiInterface::class.java)
    }


    fun <T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }




}