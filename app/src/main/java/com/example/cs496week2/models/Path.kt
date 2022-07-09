package com.example.cs496week2.models

import com.google.gson.annotations.SerializedName

data class Path(
    @SerializedName("start")
    val start: Node,
    @SerializedName("end")
    val end: Node,
    @SerializedName("length")
    val length: Int
)
