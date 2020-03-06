package com.osama.movieshow.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.osama.movieshow.data.favorites.DataBase
import com.osama.movieshow.data.movie.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class FavoritesViewModel (application: Application) : AndroidViewModel(application)  {

    private var db = DataBase.invoke(application)
    var favMovies: MutableLiveData<List<Movie>> = MutableLiveData()


    fun getAllFavorites(){

        db.getAllFavorites()
            .map {
                it.reversed()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                favMovies.value = it
            }

        /*runBlocking{
            withContext(Dispatchers.IO){
                favMovies = db.getAllFavorites()
            }
        }*/
    }


}
