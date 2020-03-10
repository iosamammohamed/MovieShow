package com.osama.movieshow.ui.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.data.remote.ApiClient
import com.osama.movieshow.data.repository.MovieRepository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MoviesViewModel() : ViewModel() {

    var url:String = ""
        set(value){
            field = value
        }

    val moviesRopository = MovieRepository()
    var movies = MutableLiveData<List<Movie>>()
    var isLoading = MutableLiveData<Boolean>(false)
    var isEmpty = MutableLiveData<Boolean>(false)


    fun getMovies(){
        isLoading.value = true
       moviesRopository.getMovies(url, object: Observer<List<Movie>>{
           override fun onSubscribe(d: Disposable) {}
           override fun onNext(moviesList: List<Movie>) {
               isLoading.value = false
               isEmpty.value = moviesList.isNullOrEmpty()
               movies.value = moviesList
           }
           override fun onError(e: Throwable) {
               isLoading.value = false
               isEmpty.value = true
           }
           override fun onComplete() {}
       })
    }
}