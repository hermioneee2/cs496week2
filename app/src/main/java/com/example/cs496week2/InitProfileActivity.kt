package com.example.cs496week2

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.example.cs496week2.databinding.ActivityInitProfileBinding


class InitProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitProfileBinding
//    private val list: MutableList<String>? = null
    private val list: MutableList<String> = mutableListOf()
    private var workCnt: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInitProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val ivProfilePic: ImageView = binding.idIVProfilePic
//        val tvName: EditText = binding.idETName
//        val etPhone: EditText = binding.idETPhone
//        val etEmail: EditText = binding.idETEmail
//        val btnSetMyProfile: Button = binding.idBTNSetMyProfile

        //set text using info from kakao account
        if (intent.hasExtra("nameKey")) {
            binding.etName.setText(intent.getStringExtra("nameKey"))
//            Log.d("name", intent.getStringExtra("nameKey").toString())
        } else {
            Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
        }

        if (intent.hasExtra("emailKey")) {
            binding.etEmail.setText(intent.getStringExtra("emailKey"))
//            Log.d("email", intent.getStringExtra("emailKey").toString())
        } else {
            Toast.makeText(this, "전달된 이메일이 없습니다", Toast.LENGTH_SHORT).show()
        }

        if (intent.hasExtra("photoSrcKey")) {
//            Log.d("email", intent.getStringExtra("photoSrcKey").toString())
            val imageStr = intent.getStringExtra("photoSrcKey").toString()
            Glide.with(this).load(imageStr).into(binding.ivProfilePic)
        } else {
            Toast.makeText(this, "전달된 포토소스가 없습니다", Toast.LENGTH_SHORT).show()
        }

        // 리스트에 검색될 데이터(단어)를 추가한다.
        settingList();

        binding.actvWork.setAdapter(
            ArrayAdapter(
                this,
                R.layout.simple_dropdown_item_1line, list as ArrayList<String>
            )
        )

        // set on-click listener for adding tags //WORK
        binding.btnWork.setOnClickListener {
            val newWork = binding.actvWork.text;
            workCnt++

            if (workCnt<=3){
                val tagName = "tvWorkTag$workCnt"
                val tagId: Int =
                    resources.getIdentifier(tagName, "id", packageName)
                val tag = findViewById<TextView>(tagId)

                tag.text = newWork
                tag.visibility = View.VISIBLE
            } else {
                Toast.makeText(this@InitProfileActivity, "Maximum 3 tags can be added.", Toast.LENGTH_SHORT).show()
            }

            binding.actvWork.setText("")
        }


        // set on-click listener after entering all data
        binding.btnSetMyProfile.setOnClickListener {
            val name = binding.etName.text;
            val phone = binding.etPhone.text;
            val email = binding.etEmail.text;
            Toast.makeText(this@InitProfileActivity, name, Toast.LENGTH_LONG).show()
            // send data
        }
    }
    private fun settingList() {
        list.add("채수빈")
        list.add("박지현")
        list.add("수지")
        list.add("남태현")
        list.add("하성운")
        list.add("크리스탈")
        list.add("강승윤")
        list.add("손나은")
        list.add("남주혁")
        list.add("루이")
        list.add("진영")
        list.add("슬기")
        list.add("이해인")
        list.add("고원희")
        list.add("설리")
        list.add("공명")
        list.add("김예림")
        list.add("혜리")
        list.add("웬디")
        list.add("박혜수")
        list.add("카이")
        list.add("진세연")
        list.add("동호")
        list.add("박세완")
        list.add("도희")
        list.add("창모")
        list.add("허영지")
        list.add("박a")
        list.add("박b")
        list.add("박c")
        list.add("박d")
        list.add("박e")
        list.add("박aa")
        list.add("박bb")
        list.add("박cv")
        list.add("박dr")
        list.add("박e")
    }


}