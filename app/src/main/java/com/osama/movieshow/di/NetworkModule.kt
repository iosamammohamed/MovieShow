package com.osama.movieshow.di

import com.osama.movieshow.data.remote.MovieApiInterface
import com.osama.movieshow.data.repository.MovieRepository
import com.osama.movieshow.ui.movies.MoviesViewModelFactory
import com.osama.movieshow.utils.Constants
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = Kodein.Module("networkModule"){
    bind<OkHttpClient>() with singleton { getOkHttpClient() }
    bind<Retrofit>() with singleton { getRetrofit(instance())}
    bind<MovieApiInterface>() with singleton { getMovieApiInterface(instance()) }
    bind<MoviesViewModelFactory>() with provider { getMoviesViewModelFactory(instance()) }
}

fun getOkHttpClient(): OkHttpClient{
    return  OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).build()
}

fun getRetrofit(okHttpClient: OkHttpClient): Retrofit{
    return  Retrofit.Builder().baseUrl(Constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client( okHttpClient )
        .build()
}

fun getMovieApiInterface(retrofit: Retrofit): MovieApiInterface{
    return  retrofit.create(MovieApiInterface::class.java)
}

fun getMoviesViewModelFactory(moviesRepository: MovieRepository): MoviesViewModelFactory{
    return  MoviesViewModelFactory(moviesRepository)
}

