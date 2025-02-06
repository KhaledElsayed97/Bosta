package dev.khaled.bosta.data.service

import dev.khaled.bosta.data.model.CityWrapper
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("cities/getAllDistricts?countryId=60e4482c7cb7d4bc4849c4d5")
    suspend fun getCities(): Response<CityWrapper>
}