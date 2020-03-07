package com.osama.movieshow.ui.movies

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
        var callMovies = MovieApiClient.getMovies(url)
        callMovies.enqueue(object: Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d("hhhhhhhhhhh ", call.request().url().toString())
                val jsonArray =
                    response.body()!!.getAsJsonArray("results")
                val moviesList: List<Movie> = Gson().fromJson(
                    jsonArray.toString(),
                    object : TypeToken<List<Movie?>?>() {}.type
                )
                movies.value = moviesList
                isEmpty.value = if(moviesList.size == 0) true else false
                isLoading.value = false

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                isEmpty.value = true
                isLoading.value = false
                Log.d("MoviesViewModel", t.message)
            }

        })
    }

}