package com.osama.movieshow.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osama.movieshow.R
import com.osama.movieshow.data.movie.Movie
import com.osama.movieshow.ui.singlemovie.SingleMovieActivity
import com.osama.movieshow.utils.Urls
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var data: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) =
        holder.bind(data[position])

    fun setData(data: List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) = with(itemView) {

            Glide.with(context).load(Urls.baseImageUrl + movie.posterPath).into(movie_logo)

            setOnClickListener {
                val intent = Intent(context, SingleMovieActivity::class.java)
                intent.putExtra(SingleMovieActivity.movieTag, movie)
                context.startActivity(intent)
            }
        }
    }
}