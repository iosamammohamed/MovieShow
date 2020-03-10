package com.osama.movieshow.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osama.movieshow.data.movie.Movie
import com.osama.movieshow.data.movie.MovieApiClient
import com.osama.movieshow.data.movie.MovieCallback

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
        MovieApiClient.getMovies(url, object: MovieCallback{
            override fun onSuccess(data: List<Movie>) {
                println("llllllllllllllll success")
                isLoading.value = false
                isEmpty.value = false
                movies.value = data
            }
            override fun onFailure(error: String) {
                println("llllllllllllllll failed")
                isLoading.value = false
                isEmpty.value = true
            }
        })
    }

}