package com.osama.movieshow.utils.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osama.movieshow.R
import com.osama.movieshow.data.movie.Movie
import com.osama.movieshow.ui.favorite.FavoritesFragment
import com.osama.movieshow.ui.singlemovie.SingleMovieActivity
import com.osama.movieshow.utils.Urls
import kotlinx.android.synthetic.main.item_favorite_movie.view.*
import java.util.*


class FavoritesAdapetr() : RecyclerView.Adapter<FavoritesAdapetr.FavoritesViewHolder>() {

    private var data: List<Movie> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorite_movie, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) =
        holder.bind(data[position])

    fun setData(data: List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }

    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) = with(itemView) {
            // TODO: Bind the data with View

            Glide.with(context).load(Urls.baseImageUrl + movie.posterPath).into(single_favorite_movie_img)
            single_favorite_movie_title.text = movie.title
            single_favorite_movie_vote_average.text = "Rate: ${movie.voteAverage}"
            single_favorite_movie_release_date.text = movie.releaseDate
            single_favorite_movie_show_details.setOnClickListener{
                var intent = Intent(context, SingleMovieActivity::class.java)
                intent.putExtra(SingleMovieActivity.movieTag, movie)
                (context as Activity).startActivityForResult(intent, FavoritesFragment.RETURNED_TO_FAV_FROM_SINGLE)
            }

            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}