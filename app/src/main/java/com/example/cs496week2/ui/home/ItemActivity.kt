package com.example.cs496week2.ui.home

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
    var workTagAdapter: TagAdapter? = null;
    var areaTagAdapter: TagAdapter? = null;
    var hobbyTagAdapter: TagAdapter? = null;
    var relationshipTagAdapter: TagAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_item)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemModal = intent.getSerializableExtra("data") as ItemModal;


        Glide.with(this).load(itemModal!!.photoSrc).into(binding.ivProfilePic)
//        Log.d("imageSrc", itemModal!!.imageSrc)
//        tvTest.text = itemModal!!.name
        binding.tvName.text = itemModal!!.name
        binding.tvEmail.text = itemModal!!.email
        binding.tvPhone.text = itemModal!!.phone

        workTagAdapter = TagAdapter()
        rvWorkTag.setHasFixedSize(true)
        workTagAdapter!!.setData(itemModal!!.workTagList)
        rvWorkTag.adapter = workTagAdapter;

        areaTagAdapter = TagAdapter()
        rvAreaTag.setHasFixedSize(true)
        areaTagAdapter!!.setData(itemModal!!.areaTagList)
        rvAreaTag.adapter = areaTagAdapter;

        hobbyTagAdapter = TagAdapter()
        rvHobbyTag.setHasFixedSize(true)
        hobbyTagAdapter!!.setData(itemModal!!.hobbyTagList)
        rvHobbyTag.adapter = hobbyTagAdapter;

        relationshipTagAdapter = TagAdapter()
        rvRelationshipTag.setHasFixedSize(true)
        relationshipTagAdapter!!.setData(itemModal!!.relationshipTagList)
        rvRelationshipTag.adapter = relationshipTagAdapter;




    }
}