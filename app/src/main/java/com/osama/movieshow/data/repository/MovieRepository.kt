package com.osama.movieshow.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.data.remote.ApiClient
import com.osama.movieshow.data.remote.MovieApiInterface
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieRepository(application: Application) {

    val moviesApi = ApiClient.buildService(MovieApiInterface::class.java)


    fun getMovies(url:String, observer: Observer<List<Movie>>){
        CompositeDisposable().add(
            getMoviesRemote(url).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                observer.onNext(it)
            },{
                observer.onError(it)
            })
        )
    }

    private fun getMoviesRemote(url:String): Observable<List<Movie>>{

        return moviesApi.getMovies(url).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.movies
            }
    }
}