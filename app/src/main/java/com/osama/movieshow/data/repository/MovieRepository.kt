package com.osama.movieshow.data.repository

import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.data.remote.MovieApiInterface
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieRepository(val moviesApi: MovieApiInterface) {


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

    private fun getMoviesFromCache(){
        //To be implemented
    }

}