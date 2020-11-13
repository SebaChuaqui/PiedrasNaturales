package com.example.piedrasnaturales.Retrofit

import com.example.piedrasnaturales.model.PiedrasItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiJoyas {

    @GET("joyas/")
    fun getJoyasFromApi(): retrofit2.Call<List<PiedrasItem>>

    @GET("joyas/")
    suspend fun getDataFromApiCoroutines(): Response<List<PiedrasItem>>

}