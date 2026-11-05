package com.example.mycyberhub.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val api: NvdApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://services.nvd.nist.gov/rest/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NvdApi::class.java)
    }
}