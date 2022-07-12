package com.example.cs496week2.ui.home

import java.io.Serializable

data class ItemModal (
    var userID: String,
    var name: String,
    var phone: String,
    var email: String,
    var workTagList: ArrayList<String>,
    var areaTagList: ArrayList<String>,
    var hobbyTagList: ArrayList<String>,
    var relationshipTagList: ArrayList<String>,
    var photoSrc: String,
    var tagList: ArrayList<String>
):Serializable