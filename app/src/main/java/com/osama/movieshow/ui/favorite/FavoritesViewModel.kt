package com.osama.movieshow.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osama.movieshow.data.favorites.DataBase
import com.osama.movieshow.data.movie.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class FavoritesViewModel (application: Application) : AndroidViewModel(application)  {

    private var db = DataBase.invoke(application)
    var favMovies = MutableLiveData<List<Movie>>()


    fun getAllFavorites(){
        lateinit var movies:List<Movie>
        runBlocking {
            withContext(Dispatchers.IO) {
                movies = db.getAllFavorites()
            }
        }
        favMovies.value = movies

    }


}
