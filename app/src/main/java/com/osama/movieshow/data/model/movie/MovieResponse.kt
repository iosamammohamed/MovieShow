package com.osama.movieshow.data.model.movie

import com.google.gson.annotations.SerializedName
import com.osama.movieshow.data.model.movie.Movie

class MovieResponse (@SerializedName("results")var movies: List<Movie>)