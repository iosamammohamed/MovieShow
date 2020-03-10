package com.osama.movieshow.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.data.repository.FavoriteRepository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class FavoritesViewModel (application: Application) : AndroidViewModel(application)  {

    private val favoritesRepository = FavoriteRepository(application)
    var favMovies: MutableLiveData<List<Movie>> = MutableLiveData()


    fun getAllFavorites(){
        favoritesRepository.getAllFavorites(object: Observer<List<Movie>>{
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(moviesList: List<Movie>) {
                favMovies.value = moviesList
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        })
    }


}
