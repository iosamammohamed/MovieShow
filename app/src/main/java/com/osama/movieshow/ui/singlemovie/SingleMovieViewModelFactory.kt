package com.osama.movieshow.ui.singlemovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osama.movieshow.data.repository.FavoriteRepository

class SingleMovieViewModelFactory(val favoriteRepository: FavoriteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SingleMovieViewModel(favoriteRepository) as T
    }
}