package com.osama.movieshow.ui.movies.fragments.upcoming

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.osama.movieshow.data.movie.Movie
import com.osama.movieshow.data.movie.MovieApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpComingMoviesViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var upComingMovies = MutableLiveData<List<Movie>>()
    val title = "Top Rated"


    fun getUpComingMovies(){
        var callUpComingMovies = MovieApiClient.getUpcomingMovies()
        callUpComingMovies.enqueue(object: Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val jsonArray =
                    response.body()!!.getAsJsonArray("results")
                val movies: List<Movie> = Gson().fromJson(
                    jsonArray.toString(),
                    object : TypeToken<List<Movie?>?>() {}.type
                )
                upComingMovies.value = movies
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("MoviesViewModel", t.message)
            }

        })
    }
}
