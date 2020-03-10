package com.osama.movieshow.data.movie

interface MovieCallback {
    fun onSuccess(data: List<Movie>)
    fun onFailure(error: String)
}