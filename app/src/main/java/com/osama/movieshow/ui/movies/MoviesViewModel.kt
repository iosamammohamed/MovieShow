package com.osama.movieshow.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osama.movieshow.data.movie.Movie
import com.osama.movieshow.data.movie.MovieApiClient
import com.osama.movieshow.data.movie.MovieCallback
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MoviesViewModel() : ViewModel() {

    var url:String = ""
        set(value){
            field = value
        }

    var movies = MutableLiveData<List<Movie>>()
    var isLoading = MutableLiveData<Boolean>(false)
    var isEmpty = MutableLiveData<Boolean>(false)


    fun getMovies(){
        isLoading.value = true
        MovieApiClient.getMovies(url, object: Observer<List<Movie>>{
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(movieList: List<Movie>) {
                isLoading.value = false
                isEmpty.value = if(movieList.isEmpty()) true else false
                movies.value = movieList
            }
            override fun onError(e: Throwable) {
                isLoading.value = false
                isEmpty.value = true
            }
            override fun onComplete() {}
        })
    }

}