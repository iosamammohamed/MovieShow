package com.osama.movieshow.ui.movies


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.osama.movieshow.R
import com.osama.movieshow.data.model.movie.Movie
import com.osama.movieshow.data.remote.MovieApiInterface
import com.osama.movieshow.data.repository.MovieRepository
import com.osama.movieshow.utils.RecyclerViewUtils
import com.rockerhieu.rvadapter.states.StatesRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


open class BaseMoviesFragment(val title:String, val url:String) : Fragment(), KodeinAware {


    override val kodein by closestKodein()

    private val moviesViewModelFactory: MoviesViewModelFactory by instance()

    private lateinit var viewModelN: MoviesViewModel
    private var moviesAdapter = MoviesAdapter()
    private val recyclerViewUtils = RecyclerViewUtils()
    private lateinit var statesRecyclerViewAdapter: StatesRecyclerViewAdapter
    private lateinit var loadingView: View
    private lateinit var emptyView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelN = ViewModelProvider(this, moviesViewModelFactory).get(MoviesViewModel::class.java)
        viewModelN.url = url
        viewModelN.getMovies()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycler()
        loadingObserve()
        dataObserve()
        emptyDataObserve()
        swipe_refresh_layout.setOnRefreshListener {
            refreshData()
        }

    }


    private fun setupRecycler(){
        recyclerViewUtils.setupStaggeredGridRecView(recycler, context)
        loadingView = layoutInflater.inflate(R.layout.recycler_loading_view, recycler, false)
        emptyView = layoutInflater.inflate(R.layout.recycler_empty_view, recycler, false)
        statesRecyclerViewAdapter = StatesRecyclerViewAdapter(moviesAdapter, loadingView, emptyView,null)
        CenterViewHolder(loadingView)
        CenterViewHolder(emptyView)
        recycler.adapter = statesRecyclerViewAdapter
    }
    private fun dataObserve(){
        viewModelN.movies.observe(viewLifecycleOwner,
            Observer<List<Movie>> { movies ->
                statesRecyclerViewAdapter.setState(StatesRecyclerViewAdapter.STATE_NORMAL)
                moviesAdapter.setData(movies!!)
            })
    }
    private fun loadingObserve(){
        viewModelN.isLoading.observe(viewLifecycleOwner,
            Observer<Boolean> { isLoading ->
                Log.d("hhhhhhhhhh loading = ", isLoading.toString())
                if (isLoading!!)
                    statesRecyclerViewAdapter.setState(StatesRecyclerViewAdapter.STATE_LOADING)
                else
                    swipe_refresh_layout.isRefreshing = false
            })
    }
    private fun emptyDataObserve(){
        viewModelN.isEmpty.observe(viewLifecycleOwner,
            Observer<Boolean> { isEmpty ->
                Log.d("hhhhhhhhhhhhh empty = ", isEmpty.toString())
                if (isEmpty!!)
                    statesRecyclerViewAdapter.setState(StatesRecyclerViewAdapter.STATE_EMPTY)
            })
    }
    private fun refreshData(){
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
