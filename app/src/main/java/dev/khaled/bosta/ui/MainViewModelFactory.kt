package dev.khaled.bosta.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.khaled.bosta.data.repo.CityRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: CityRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}