package com.osama.movieshow.ui.movies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.osama.movieshow.R
import com.osama.movieshow.ui.movies.fragments.LatestBaseMoviesFragment
import com.osama.movieshow.ui.movies.fragments.PopularBaseMoviesFragment
import com.osama.movieshow.ui.movies.fragments.TopRatedBaseMoviesFragment
import com.osama.movieshow.ui.movies.fragments.UpComingBaseMoviesFragment
import kotlinx.android.synthetic.main.fragment_movies_pager.*


/**
 * A simple [Fragment] subclass.
 */
class MoviesPagerFragment : Fragment() {

    lateinit var moviesAdapter: MoviesViewPagerAdapter
    val popularTitleMoviesFragment =
        PopularBaseMoviesFragment()
    val latestTitleMoviesFragment =
        LatestBaseMoviesFragment()
    val topRatedTitleMoviesFragment =
        TopRatedBaseMoviesFragment()
    val upComingTitleMoviesFragment =
        UpComingBaseMoviesFragment()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabbedViewPager()
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
        view_pager.adapter = moviesAdapter
        tabs.setupWithViewPager(view_pager)
    }

    class MoviesViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

        var movieFragments = ArrayList<BaseMoviesFragment>()



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
