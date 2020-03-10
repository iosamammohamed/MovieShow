package com.osama.movieshow.ui.singlemovie

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.osama.movieshow.R
import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.utils.Constants
import kotlinx.android.synthetic.main.activity_single_movie.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*


class SingleMovieActivity : AppCompatActivity() {

    companion object{
        val movieTag = "movie"
    }
    lateinit var movie: Movie
    lateinit var viewModel:SingleMovieViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)
//        viewModel = ViewModelProviders.of(this).get(SingleMovieViewModel::class.java)
            viewModel = SingleMovieViewModel(application)

        movie = intent.getParcelableExtra(movieTag)

        setupToolbar()
        setupMovieData()

        viewModel.isFavorite(movie.id)
        viewModel.isFav.observe(this,object: Observer<Boolean>{
            override fun onChanged(isFav: Boolean?) {
              changeFavIcon(isFav!!)
            }

        })

        single_movie_favorite_border.setOnClickListener { viewModel.addMovie(movie) }
        single_movie_favorite_fill.setOnClickListener {  viewModel.deleteMovie(movie) }

    }

    private fun changeFavIcon(isFav:Boolean){
        if(isFav){
            single_movie_favorite_border.visibility = GONE
            single_movie_favorite_fill.visibility = VISIBLE
        }
         else{
            single_movie_favorite_border.visibility = VISIBLE
            single_movie_favorite_fill.visibility = GONE
        }
    }

    private fun setupToolbar(){
        toolbar.title = ""
        toolbar.toolbar_title.text = movie.title

        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)




    }

    private fun setupMovieData(){
        Glide.with(this).load(Constants.baseImageUrl + movie.posterPath).into(single_movie_poster)
        single_movie_title.text = movie.title
        single_movie_overview.text = movie.overview
        single_movie_vote_average.text = "Rate: ${movie.voteAverage}"
        single_movie_release_date.text = movie.releaseDate
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // handle arrow click here
        if (item.getItemId() === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
/*
    override fun onBackPressed() {
        setResult(RESULT_OK)
        super.onBackPressed()
    }*/


}
