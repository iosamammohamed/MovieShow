package com.osama.movieshow.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.osama.movieshow.data.model.movie.Movie

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun FavDao(): FavoriteDao

    companion object {
        @Volatile private var instance: DataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance
            ?: synchronized(LOCK){
            instance
                ?: buildDatabase(
                    context
                ).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            DataBase::class.java, "movie_show.db")
            .build()
    }



    fun getAllFavorites(observer: Observer<List<Movie>>) {
        CompositeDisposable().add(
            FavDao().getAllFavorites()
                .map {
                    it.reversed()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    observer.onNext(it)
                }
        )
    }

    

}