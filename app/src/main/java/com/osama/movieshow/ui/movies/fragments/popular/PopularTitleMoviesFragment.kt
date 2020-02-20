package com.osama.movieshow.ui.movies.fragments.popular

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.osama.movieshow.R
import com.osama.movieshow.data.movie.Movie
import com.osama.movieshow.ui.movies.fragments.TitleMoviesFragment
import com.osama.movieshow.utils.adapters.MoviesAdapter
import com.osama.movieshow.utils.RecyclerViewUtils
import kotlinx.android.synthetic.main.fragment_movies.*

class PopularTitleMoviesFragment : TitleMoviesFragment("popular") {

    companion object {
        fun newInstance() =
            PopularTitleMoviesFragment()
    }

    private lateinit var viewModel: PopularMoviesViewModel
    var moviesAdapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel::class.java)
        viewModel.getPopularMovies()
        println("rrrrrrrrrrrrrrrrrrrrrrrr popular")

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerViewUtils = RecyclerViewUtils()
        recyclerViewUtils.setupGridRecView(recycler, context, 2)
        recycler.adapter = moviesAdapter

        viewModel.popularMovies.observe(viewLifecycleOwner, object: Observer<List<Movie>>{
            override fun onChanged(movies: List<Movie>?) {
                moviesAdapter.setData(movies!!)
            }

        })

    }



}
