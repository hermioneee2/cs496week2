package com.example.cs496week2.models

import com.google.gson.annotations.SerializedName

// Property in Neo4j DB that Relations and Nodes can have
data class Property(
    @SerializedName("userID")
    val userID : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("phone")
    val phone : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("photoSrc")
    val photoSrc : String,
)