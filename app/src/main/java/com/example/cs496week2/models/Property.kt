package com.example.cs496week2.models

import com.google.gson.annotations.SerializedName

// Property in Neo4j DB that Relations and Nodes can have
data class Property(
    @SerializedName("name")
    val name : String,
    @SerializedName("Surname")
    val Surname : String
)