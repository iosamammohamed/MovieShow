package com.osama.movieshow.data.favorites

import androidx.lifecycle.LiveData
import androidx.room.*
import com.osama.movieshow.data.movie.Movie
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Insert
    fun addMovie(movie:Movie)

    @Delete
    fun deleteMovie(movie:Movie):Int

    @Query("Delete from favorites")
    fun deleteAllMovies():Int

    @Query("select Count(*) from favorites where id = :id")
     fun isFavorite(id: String):Int


    @Query("select Count(*) from favorites")
    fun getFavoritesCount():Int

    @Query("select * from favorites")
    fun getAllFavorites():List<Movie>

}