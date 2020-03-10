package com.osama.movieshow.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.osama.movieshow.data.favorites.DataBase
import com.osama.movieshow.data.movie.Movie
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class FavoritesViewModel (application: Application) : AndroidViewModel(application)  {

    private var db = DataBase.invoke(application)
    var favMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    lateinit var observer: Observer<List<Movie>>


    fun getAllFavorites(){

        observer = object: Observer<List<Movie>>{
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(moviesList: List<Movie>) {
                favMovies.value = moviesList
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {}


        }


        db.getAllFavorites(observer)

    }


}
