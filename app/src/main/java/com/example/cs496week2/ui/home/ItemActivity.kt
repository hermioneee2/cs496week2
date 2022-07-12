package com.example.cs496week2.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cs496week2.databinding.ActivityItemBinding
import com.example.cs496week2.interfaces.GetUserAPI
import com.example.cs496week2.objects.MyProfile
import com.example.cs496week2.objects.RetrofitHelper
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.row_items.*
import kotlinx.android.synthetic.main.row_items.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        btnEdit.visibility = View.GONE
        btnDirect.visibility = View.VISIBLE
        btnOnBehalfOf.visibility = View.VISIBLE

        // set on-click listener for direct message
        binding.btnDirect.setOnClickListener {
            // send SMS directly
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + itemModal!!.phone))
//            intent.putExtra("sms_body", "안녕하세요 " + itemModal!!.name + "님, '한다리건너'를 통해 알게 되어 연락 드립니다.")
//            startActivity(intent)
        }

        // set on-click listener for direct message
        binding.btnOnBehalfOf.setOnClickListener {
            // notify the bridge person

            val getUserAPI = RetrofitHelper.getInstance().create(GetUserAPI::class.java)
            GlobalScope.launch {
                val result = getUserAPI.getUserList(
                    param1 = MyProfile.userID,
                    param2 = itemModal!!.userID,
                    body = listOf()
                )
                Log.d("ItemActivity set temp link: ", result.body().toString())

            }
            Toast.makeText(this@ItemActivity, "이어주는 연결 다리에게 연락이 갔어요!", Toast.LENGTH_LONG).show()
        }


    }
}