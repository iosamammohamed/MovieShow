package com.osama.movieshow.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.data.repository.FavoriteRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoritesViewModel (val favoritesRepository: FavoriteRepository): ViewModel()  {

    var favMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()


    fun getAllFavorites(){
        compositeDisposable.add(favoritesRepository.getAllFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {moviesList ->
                    favMovies.value = moviesList
                },
                {
                }
            )
        )
    }


}
