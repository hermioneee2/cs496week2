package com.example.cs496week2.models
import com.google.gson.annotations.SerializedName

// Node in Neo4j DB
data class Node(
    @SerializedName("identity")
    val identity: Int,
    @SerializedName("labels")
    val labels: List<String>,
    @SerializedName("properties")
    val properties: Property
)