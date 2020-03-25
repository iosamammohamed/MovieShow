package com.osama.movieshow.data.repository

import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.data.remote.MovieApiInterface
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieRepository(val moviesApi: MovieApiInterface) {


    fun getMovies(url:String): Observable<List<Movie>>{
        return getMoviesRemote(url)
    }

    private fun getMoviesRemote(url:String): Observable<List<Movie>>{

        return moviesApi.getMovies(url).toObservable()
            .map { it.movies }
    }

    private fun getMoviesFromCache(){
        //To be implemented
    }

}