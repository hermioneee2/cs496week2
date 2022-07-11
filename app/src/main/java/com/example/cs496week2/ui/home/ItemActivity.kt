package com.example.cs496week2.ui.home

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.cs496week2.R
import com.example.cs496week2.databinding.ActivityInitProfileBinding
import com.example.cs496week2.databinding.ActivityItemBinding
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.row_items.*
import kotlinx.android.synthetic.main.row_items.view.*

class ItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemBinding
    var itemModal: ItemModal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_item)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemModal = intent.getSerializableExtra("data") as ItemModal;


        Glide.with(this).load(itemModal!!.imageSrc).into(binding.ivProfilePic)
        Log.d("imageSrc", itemModal!!.imageSrc)
        tvTest.text = itemModal!!.name


    }
}