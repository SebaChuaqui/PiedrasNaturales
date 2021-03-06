package com.example.piedrasnaturales.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {

    companion object{

        private const val URL_BASE = "https://my-json-server.typicode.com/SebaChuaqui/PiedrasNaturales/"

        fun getRetroJoyas(): ApiJoyas{

            val mRetrofitJoyas = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofitJoyas.create(ApiJoyas::class.java)

        }
    }
}