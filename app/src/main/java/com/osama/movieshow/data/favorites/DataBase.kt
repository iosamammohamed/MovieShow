package com.osama.movieshow.data.favorites

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.osama.movieshow.data.movie.Movie

import io.reactivex.Observable

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun FavDao():FavoriteDao

    companion object {
        @Volatile private var instance: DataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            DataBase::class.java, "movie_show.db")
            .build()
    }



    fun getAllFavorites(): Observable<List<Movie>> {
        return FavDao().getAllFavorites()
    }

}