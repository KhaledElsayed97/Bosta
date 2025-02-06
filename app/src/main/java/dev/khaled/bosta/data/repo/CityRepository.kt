package dev.khaled.bosta.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.khaled.bosta.data.model.CityWrapper
import dev.khaled.bosta.data.service.ApiService
import javax.inject.Inject

class CityRepository @Inject constructor(private val apiService: ApiService) {

    private val _cities = MutableLiveData<CityWrapper>()
    val cities: LiveData<CityWrapper>
        get() =_cities

    private val _errorResult = MutableLiveData<String>()
    val errorResult: LiveData<String>
        get() = _errorResult

    suspend fun getCities() {
        try {
            if(apiService.getCities().isSuccessful){
                if(apiService.getCities().body()!=null)
                    _cities.postValue(apiService.getCities().body())
            }
            else{
                _errorResult.postValue(apiService.getCities().errorBody().toString())
            }
        }catch (e: Exception) {
            _errorResult.postValue(e.message.toString())
        }
    }
}