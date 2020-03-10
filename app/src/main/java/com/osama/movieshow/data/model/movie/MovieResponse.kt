package com.osama.movieshow.data.model.movie

import com.google.gson.annotations.SerializedName

class MovieResponse (@SerializedName("results")var movies: List<Movie>)