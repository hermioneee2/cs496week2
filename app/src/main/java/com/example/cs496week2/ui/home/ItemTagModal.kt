package com.example.cs496week2.ui.home

import java.io.Serializable

data class ItemTagModal (
    var tag: String,
    var profileList: MutableList<ItemModal>
):Serializable