package com.osama.movieshow.di

import com.osama.movieshow.data.local.FavoriteDao
import com.osama.movieshow.data.remote.MovieApiInterface
import com.osama.movieshow.data.repository.FavoriteRepository
import com.osama.movieshow.data.repository.MovieRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val repositoryModule = Kodein.Module("repositoryModule"){
    bind<MovieRepository>() with singleton { getMovieRepository(instance()) }
    bind<FavoriteRepository>() with singleton { getFavoriteRepository(instance()) }
}

fun getMovieRepository(moviesApi: MovieApiInterface): MovieRepository{
    return  MovieRepository(moviesApi)
}

fun getFavoriteRepository(favoritesDao: FavoriteDao): FavoriteRepository{
    return  FavoriteRepository(favoritesDao)
}