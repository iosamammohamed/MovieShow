package com.osama.movieshow.ui.movies.fragments.latest

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

class LatestMoviesViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var latestMovies = MutableLiveData<List<Movie>>()
    val title = "Latest"


    fun getLatestMovies(){
        var callLatestMovies = MovieApiClient.getLatestMovies()
        callLatestMovies.enqueue(object: Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val jsonArray =
                    response.body()!!.getAsJsonArray("results")
                val movies: List<Movie> = Gson().fromJson(
                    jsonArray.toString(),
                    object : TypeToken<List<Movie?>?>() {}.type
                )
                println("hhhhhhhhhhhhh ${movies.toString()}")
                latestMovies.value = movies
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("MoviesViewModel", t.message)
            }

        })
    }
}
