package com.example.cs496week2.interfaces

import com.example.cs496week2.models.Node
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetUserAPI {
    @GET("/{id}")
    suspend fun getUser(@Path("id") id : String) : Response<Node>

    @GET("/{id}")
    suspend fun getFriends(@Path("id") id : String,
                           @Query("param1") param1 : String? = null) : Response<List<Node>>
}