package com.osama.movieshow.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.osama.movieshow.R
import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.utils.RecyclerViewUtils
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FavoritesFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private val favoritesViewModelFactory: FavoritesViewModelFactory by instance()

    private lateinit var viewModel: FavoritesViewModel
    var moviesAdapter = FavoritesAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, favoritesViewModelFactory).get(FavoritesViewModel::class.java)
        viewModel.getAllFavorites()

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerViewUtils = RecyclerViewUtils()
        recyclerViewUtils.setupLinearVerticalRecView(recycler, context)
        recycler.adapter = moviesAdapter


        viewModel.favMovies.observe(viewLifecycleOwner,
            Observer<List<Movie>> { movies -> moviesAdapter.setData(movies!!) })

    }


}
