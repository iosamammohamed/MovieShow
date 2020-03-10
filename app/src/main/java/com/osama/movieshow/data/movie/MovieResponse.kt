package com.osama.movieshow.data.movie

import com.google.gson.annotations.SerializedName

class MovieResponse (@SerializedName("results")var movies: List<Movie>)