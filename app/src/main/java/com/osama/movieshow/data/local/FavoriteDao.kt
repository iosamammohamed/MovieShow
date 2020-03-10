package com.osama.movieshow.data.local

import androidx.room.*
import com.osama.movieshow.data.model.movie.Movie
import io.reactivex.Observable


@Dao
interface FavoriteDao {

    @Insert
    fun addMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie):Int

    @Query("Delete from favorites")
    fun deleteAllMovies():Int

    @Query("select Count(*) from favorites where id = :id")
     fun isFavorite(id: String):Int


    @Query("select Count(*) from favorites")
    fun getFavoritesCount():Int

    @Query("select * from favorites")
    fun getAllFavorites(): Observable<List<Movie>>

}