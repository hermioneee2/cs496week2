package com.example.cs496week2.objects

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val baseUrl = "http://172.10.5.98:443" // Our server IP
    private val gson : Gson = GsonBuilder()
        .setLenient()
        .create()
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson)) // Json object to kotlin object
            .build()
    }
}