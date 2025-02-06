package dev.khaled.bosta.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.khaled.bosta.data.model.CityWrapper
import dev.khaled.bosta.data.repo.CityRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CityRepository) : ViewModel() {

    val citiesLiveData: LiveData<CityWrapper>
        get() = repository.cities

    val errorLiveData: LiveData<String>
        get() = repository.errorResult

    val loading = MutableLiveData<Boolean>()

    fun fetchCities() {
        viewModelScope.launch {
            loading.postValue(true)
            repository.getCities()
        }
    }
}