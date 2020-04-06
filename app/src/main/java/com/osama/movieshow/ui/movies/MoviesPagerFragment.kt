package com.osama.movieshow.ui.movies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.osama.movieshow.R
import com.osama.movieshow.ui.movies.fragments.LatestMoviesFragment
import com.osama.movieshow.ui.movies.fragments.PopularMoviesFragment
import com.osama.movieshow.ui.movies.fragments.TopRatedMoviesFragment
import com.osama.movieshow.ui.movies.fragments.UpComingMoviesFragment
import kotlinx.android.synthetic.main.fragment_movies_pager.*


class MoviesPagerFragment : Fragment() {

    private lateinit var moviesAdapter: MoviesViewPagerAdapter
    private val popularTitleMoviesFragment = PopularMoviesFragment()
    private val latestTitleMoviesFragment = LatestMoviesFragment()
    private val topRatedTitleMoviesFragment = TopRatedMoviesFragment()
    private val upComingTitleMoviesFragment = UpComingMoviesFragment()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_pager, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTabbedViewPager()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = moviesAdapter
        view_pager.offscreenPageLimit = 4
        tabs.setupWithViewPager(view_pager)
    }


    private fun initTabbedViewPager(){
        moviesAdapter =
            MoviesViewPagerAdapter(
                childFragmentManager
            )
        moviesAdapter.add(popularTitleMoviesFragment)
        moviesAdapter.add(latestTitleMoviesFragment)
        moviesAdapter.add(topRatedTitleMoviesFragment)
        moviesAdapter.add(upComingTitleMoviesFragment)
    }

    class MoviesViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

        private var movieFragments = ArrayList<BaseMoviesFragment>()

        fun add(fragmentBase: BaseMoviesFragment){
            this.movieFragments.add(fragmentBase)
        }

        override fun getItem(position: Int): Fragment {
            return this.movieFragments.get(position)
        }

        override fun getCount(): Int {
            return movieFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return movieFragments.get(position).title
        }
    }



}
