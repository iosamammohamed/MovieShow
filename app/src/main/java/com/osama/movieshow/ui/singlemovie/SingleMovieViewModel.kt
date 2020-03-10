package com.osama.movieshow.ui.singlemovie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.data.repository.FavoriteRepository
import kotlinx.coroutines.*

class SingleMovieViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteRepository = FavoriteRepository(application)
    var isFav = MutableLiveData<Boolean>()
    private var existInFavorite = false

    fun addMovie(movie: Movie){
        runBlocking {
            withContext(Dispatchers.IO){
                favoriteRepository.addMovieToFavorites(movie)
            }
        }
        isFavorite(movie.id)
    }

    fun deleteMovie(movie: Movie){
        runBlocking {
            withContext(Dispatchers.IO){
                favoriteRepository.deleteMovieFromFavorites(movie)
            }
        }
        isFavorite(movie.id)
    }

    fun isFavorite(id:String){
         runBlocking {
             withContext(Dispatchers.IO){
                existInFavorite = if(favoriteRepository.isMovieInFavorites(id) > 0) true else false
             }
         }
         isFav.value = existInFavorite
     }



}