package com.osama.movieshow.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.osama.movieshow.R
import com.osama.movieshow.data.movie.Movie
import com.osama.movieshow.utils.RecyclerViewUtils
import com.osama.movieshow.utils.adapters.FavoritesAdapetr
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: FavoritesViewModel
    var moviesAdapter = FavoritesAdapetr()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = FavoritesViewModel(activity!!.application)
        viewModel.getAllFavorites()

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel

        val recyclerViewUtils = RecyclerViewUtils()
        recyclerViewUtils.setupLinearVerticalRecView(recycler, context)
        recycler.adapter = moviesAdapter


        viewModel.favMovies.observe(viewLifecycleOwner, object: Observer<List<Movie>> {
            override fun onChanged(movies: List<Movie>?) {
                moviesAdapter.setData(movies!!)
            }

        })

    }


}
