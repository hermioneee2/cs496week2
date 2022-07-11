package com.example.cs496week2.interfaces

import com.example.cs496week2.models.Node
import retrofit2.Response
import retrofit2.http.*

interface GetUserAPI {
    // Get the Node that has corresponding id (everything is null if non-existent)
    @GET("Person/{id}")
    suspend fun getUser(
        @Path("id") id: String,
        @Query("param1") param1: String? = "getUser"
    ): Response<Node>

    // Post the user Node that has id to the database
    @POST("Person/{id}")
    suspend fun postUser(
        @Path("id") id: String,
        @Body body: Node
    ): Response<Node>

    // Get Friend Node list of the id Node
    @GET("Person/{id}")
    suspend fun getFriends(
        @Path("id") id: String,
        @Query("param1") param1: String? = "getFriends"
    ): Response<List<Node>>

    // List of Node pairs that wants to be connected
    @GET("Person/{id}")
    suspend fun getTempLinks(
        @Path("id") id: String,
        @Query("param1") param1: String? = "getTempLinks"
    ): Response<List<List<Node>>>

    // 1. if param1 == "getPhoneList", list of Nodes that correspond to phone numbers is returned.
    // 2. if param1 == "getTagList" and param2 == user ID that we want to query,
    // list of Nodes that represent Persons that have tags in body is returned
    // 3. 'param1 - [:TEMP_LINK] -> param2' is generated
    @POST("Person")
    suspend fun getUserList(
        @Query("param1") param1: String,
        @Query("param2") param2: String,
        @Body body: List<String>
    ): Response<List<Node>>

    // List of Tags that belong to param2's category (Work, Area, Hobby, Relationship, ...)
    @GET("Tag")
    suspend fun getTags(
        @Query("param1") param1: String? = "getTags",
        @Query("param2") param2: String,
    ): Response<List<String>>
}