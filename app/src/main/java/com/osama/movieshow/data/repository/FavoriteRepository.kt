package com.osama.movieshow.data.repository

import android.app.Application
import com.osama.movieshow.data.local.DataBase
import com.osama.movieshow.data.model.movie.Movie
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoriteRepository(application: Application) {

    private val favoritesDao = DataBase.invoke(application).FavDao()


    fun getAllFavorites(observer: Observer<List<Movie>>){
        CompositeDisposable().add(
            getAllFavoritesFromCache()!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    observer.onNext(it)
                }
        )
    }

    private fun getAllFavoritesFromCache(): Observable<List<Movie>>? {
            return favoritesDao.getAllFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.reversed()
                }
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