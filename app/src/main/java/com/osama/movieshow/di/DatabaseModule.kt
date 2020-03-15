package com.osama.movieshow.di

import android.content.Context
import com.osama.movieshow.data.local.DataBase
import com.osama.movieshow.data.local.FavoriteDao
import com.osama.movieshow.data.repository.FavoriteRepository
import com.osama.movieshow.ui.favorite.FavoritesViewModelFactory
import com.osama.movieshow.ui.singlemovie.SingleMovieViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


val databaseModule  = Kodein.Module("databaseModule"){
    bind() from singleton { getDatabase(instance()) }
    bind<FavoriteDao>() with singleton { getFavoriteDao(instance()) }
    bind<FavoritesViewModelFactory>() with provider { getFavoritesViewModelFactory(instance()) }
    bind<SingleMovieViewModelFactory>() with provider { getSingleMovieViewModelFactory(instance()) }
}

fun getDatabase(context: Context): DataBase{
    return DataBase.invoke(context)
}

fun getFavoriteDao(database: DataBase): FavoriteDao{
    return database.FavDao()
}


fun getFavoritesViewModelFactory(favoritesRepository: FavoriteRepository): FavoritesViewModelFactory{
    return FavoritesViewModelFactory(favoritesRepository)
}

fun getSingleMovieViewModelFactory(favoriteRepository: FavoriteRepository): SingleMovieViewModelFactory{
    return SingleMovieViewModelFactory(favoriteRepository)

}