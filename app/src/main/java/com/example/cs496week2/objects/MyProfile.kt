package com.example.cs496week2.objects

import com.example.cs496week2.models.Property
import com.google.gson.annotations.SerializedName

object MyProfile {
    var userID: String = ""
    var name: String = ""
    var phone: String = ""
    var email: String = ""
    var photoSrc: String = ""
    var work: List<String> = listOf()
    var hobby: List<String> = listOf()
    var area: List<String> = listOf()
    var relationship: String = ""
    var friendList: List<String> = listOf()
}