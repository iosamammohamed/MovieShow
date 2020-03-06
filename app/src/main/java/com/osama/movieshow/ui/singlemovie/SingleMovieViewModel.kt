package com.osama.movieshow.ui.singlemovie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osama.movieshow.data.favorites.DataBase
import com.osama.movieshow.data.movie.Movie
import kotlinx.coroutines.*
import kotlin.random.Random

class SingleMovieViewModel(application: Application) : AndroidViewModel(application) {


    private var db = DataBase.invoke(application)
    var isFav = MutableLiveData<Boolean>()
    private var existInFavorite = false

    init {

    }

    fun addMovie(movie:Movie){
        runBlocking {
            withContext(Dispatchers.IO){
                db.FavDao().addMovie(movie)
            }
        }
        isFavorite(movie.id)
    }

    fun deleteMovie(movie:Movie){
        runBlocking {
            withContext(Dispatchers.IO){
                db.FavDao().deleteMovie(movie)
            }
        }
        isFavorite(movie.id)
    }

    fun isFavorite(id:String){
         runBlocking {
             withContext(Dispatchers.IO){
                existInFavorite = if(db.FavDao().isFavorite(id) > 0) true else false
             }
         }
         isFav.value = existInFavorite
     }



}