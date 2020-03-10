package com.osama.movieshow.data.remote

import com.osama.movieshow.utils.Urls
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    val retrofit:Retrofit
    val movieApiInterface: MovieApiInterface
    val okHttpClient: OkHttpClient

    init{
        okHttpClient = OkHttpClient.Builder().connectTimeout(15,TimeUnit.SECONDS).build()
        retrofit = Retrofit.Builder().baseUrl(Urls.baseUrl)
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