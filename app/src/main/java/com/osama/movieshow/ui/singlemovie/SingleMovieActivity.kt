package com.osama.movieshow.ui.singlemovie

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.osama.movieshow.R
import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.utils.Constants
import kotlinx.android.synthetic.main.activity_single_movie.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class SingleMovieActivity : AppCompatActivity(), KodeinAware {

    companion object{
        const val movieTag = "movie"
    }

    override val kodein by closestKodein()
    private val singleMovieViewModelFactory: SingleMovieViewModelFactory by instance()

    lateinit var movie: Movie
    lateinit var viewModel:SingleMovieViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)
//        viewModel = ViewModelProviders.of(this).get(SingleMovieViewModel::class.java)
            viewModel = ViewModelProvider(this, singleMovieViewModelFactory).get(SingleMovieViewModel::class.java)

        movie = intent.getParcelableExtra(movieTag)!!

        setupToolbar()
        setupMovieData()

        viewModel.isFavorite(movie.id)
        viewModel.isFav.observe(this,
            Observer<Boolean> { isFav -> changeFavIcon(isFav!!) })

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
/*
    override fun onBackPressed() {
        setResult(RESULT_OK)
        super.onBackPressed()
    }*/


}
