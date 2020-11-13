package com.example.piedrasnaturales.Retrofit

import PiedrasItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiJoyas {

    @GET("zadik/")
    fun GetJoyasFromApi(): retrofit2.Call<List<PiedrasItem>>

    @GET("zadik/")
    suspend fun getDataFromApiCoroutines(): Response<List<PiedrasItem>>

}