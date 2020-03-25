package com.osama.movieshow.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel(val moviesRepository: MovieRepository) : ViewModel() {

    var url:String = ""
    var movies = MutableLiveData<List<Movie>>()
    var isLoading = MutableLiveData<Boolean>(false)
    var isEmpty = MutableLiveData<Boolean>(false)
    private val compositeDisposable = CompositeDisposable()

    fun getMovies(){
        isLoading.value = true
        compositeDisposable.add(moviesRepository.getMovies(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {moviesList ->
                    isLoading.value = false
                    isEmpty.value = moviesList.isNullOrEmpty()
                    movies.value = moviesList
                },
                {
                    isLoading.value = false
                    isEmpty.value = true
                }
            )
        )
    }


}