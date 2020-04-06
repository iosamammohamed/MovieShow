package com.osama.movieshow.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.osama.movieshow.R
import com.osama.movieshow.ui.favorite.FavoritesFragment
import com.osama.movieshow.ui.movies.MoviesPagerFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    private val moviesPagerFragment = MoviesPagerFragment()
    private val favoritesFragment = FavoritesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(moviesPagerFragment)
        bottom_nav.setOnNavigationItemSelectedListener(this)

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_bottom_movies -> replaceFragment(moviesPagerFragment)
            R.id.menu_bottom_favorites -> replaceFragment(favoritesFragment)
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.host_fragment, fragment).commit()
    }

/*    override fun onBackPressed() {
        finish()
    }*/

 /*   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == FavoritesFragment.RETURNED_TO_FAV_FROM_SINGLE && resultCode == Activity.RESULT_OK){
            this.favoritesFragment.refreshFavorites()
        }
    }
*/


}
