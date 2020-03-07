package com.osama.movieshow.ui.movies


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.osama.movieshow.R
import com.osama.movieshow.data.movie.Movie
import com.osama.movieshow.utils.RecyclerViewUtils
import com.osama.movieshow.utils.adapters.MoviesAdapter
import com.rockerhieu.rvadapter.states.StatesRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_movies.*


/**
 * A simple [Fragment] subclass.
 */
open class BaseMoviesFragment(val title:String, val url:String) : Fragment() {

    lateinit var viewModelN: MoviesViewModel
    var moviesAdapter = MoviesAdapter()

    val recyclerViewUtils = RecyclerViewUtils()


    lateinit private var statesRecyclerViewAdapter: StatesRecyclerViewAdapter
    lateinit private var loadingView: View
    lateinit private var emptyView: View
    lateinit private var errorView: View



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelN = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycler()



        viewModelN.url = url
        viewModelN.isLoading.observe(viewLifecycleOwner, object: Observer<Boolean>{
            override fun onChanged(isLoading: Boolean?) {
                Log.d("hhhhhhhhhh loading = ", isLoading.toString())
                if (isLoading!!)
                   statesRecyclerViewAdapter.setState(StatesRecyclerViewAdapter.STATE_LOADING)
                else
                    swipe_refresh_layout.isRefreshing = false
            }
        })
        viewModelN.isEmpty.observe(viewLifecycleOwner, object: Observer<Boolean>{
            override fun onChanged(isEmpty: Boolean?) {
                Log.d("hhhhhhhhhhhhh empty = ", isEmpty.toString())
                if (isEmpty!!)
                   statesRecyclerViewAdapter.setState(StatesRecyclerViewAdapter.STATE_EMPTY)
            }
        })

        viewModelN.getMovies()
        viewModelN.movies.observe(viewLifecycleOwner, object: Observer<List<Movie>> {
            override fun onChanged(movies: List<Movie>?) {
                statesRecyclerViewAdapter.setState(StatesRecyclerViewAdapter.STATE_NORMAL)
                moviesAdapter.setData(movies!!)
            }

        })

        swipe_refresh_layout.setOnRefreshListener {
            refreshData()
        }

    }


    fun setupRecycler(){
        recyclerViewUtils.setupStaggeredGridRecView(recycler, context)
        loadingView = layoutInflater.inflate(R.layout.recycler_loading_view, recycler, false)
        emptyView = layoutInflater.inflate(R.layout.recycler_empty_view, recycler, false)
        statesRecyclerViewAdapter = StatesRecyclerViewAdapter(moviesAdapter, loadingView, emptyView,null)
        CenterViewHolder(loadingView)
        CenterViewHolder(emptyView)
        recycler.adapter = statesRecyclerViewAdapter
    }

    fun refreshData(){
        viewModelN.getMovies()
    }


    class CenterViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        // Set fullSpan to fit screen
        private fun useFullSpan() {
            val layoutParams =
                StaggeredGridLayoutManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            layoutParams.isFullSpan = true
            itemView.layoutParams = layoutParams
        }

        init {
            useFullSpan()
        }
    }



}
