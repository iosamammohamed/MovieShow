package com.osama.movieshow.data.repository

import android.app.Application
import com.osama.movieshow.data.local.DataBase
import com.osama.movieshow.data.local.FavoriteDao
import com.osama.movieshow.data.model.movie.Movie
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoriteRepository(val favoritesDao: FavoriteDao) {

    fun getAllFavorites(): Observable<List<Movie>>{
       return getAllFavoritesFromCache()
    }

    private fun getAllFavoritesFromCache(): Observable<List<Movie>> {
            return favoritesDao.getAllFavorites()
                .map { it.reversed() }
    }


    fun addMovieToFavorites(movie: Movie){
        favoritesDao.addMovie(movie)
    }

    fun deleteMovieFromFavorites(movie: Movie){
        favoritesDao.deleteMovie(movie)
    }


    fun deleteAllFavorites(){
        favoritesDao.deleteAllMovies()
    }

    fun isMovieInFavorites(id: String): Int{
        return favoritesDao.isFavorite(id)
    }


}