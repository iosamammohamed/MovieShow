package com.osama.movieshow.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.osama.movieshow.data.local.DataBase
import com.osama.movieshow.data.model.movie.Movie
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class FavoritesViewModel (application: Application) : AndroidViewModel(application)  {

    private var db = DataBase.invoke(application)
    var favMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    lateinit var observer: Observer<List<Movie>>


    fun getAllFavorites(){
        db.getAllFavorites(object: Observer<List<Movie>>{
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(moviesList: List<Movie>) {
                favMovies.value = moviesList
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        })
    }


}
