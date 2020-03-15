package com.osama.movieshow.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osama.movieshow.data.repository.FavoriteRepository

class FavoritesViewModelFactory(val favoritesRepository: FavoriteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritesViewModel(favoritesRepository) as T
    }
}