package com.osama.movieshow.ui.movies.fragments.toprated

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

class TopRatedMoviesViewModel : ViewModel() {
    var topRatedMovies = MutableLiveData<List<Movie>>()
    val title = "Top Rated"


    fun getTopRatedMovies(){
        var callTopRatedMovies = MovieApiClient.getTopRatedMovies()
        callTopRatedMovies.enqueue(object: Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val jsonArray =
                    response.body()!!.getAsJsonArray("results")
                val movies: List<Movie> = Gson().fromJson(
                    jsonArray.toString(),
                    object : TypeToken<List<Movie?>?>() {}.type
                )
                topRatedMovies.value = movies
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("MoviesViewModel", t.message)
            }

        })
    }
}
