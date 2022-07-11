package com.example.cs496week2

import android.R
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.example.cs496week2.databinding.ActivityInitProfileBinding
import com.example.cs496week2.interfaces.GetUserAPI
import com.example.cs496week2.objects.MyProfile
import com.example.cs496week2.objects.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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
        binding.etName.setText(MyProfile.name)
        binding.etEmail.setText(MyProfile.email)

        val imageStr = MyProfile.photoSrc
        Glide.with(this).load(imageStr).into(binding.ivProfilePic)

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

            if (workCnt <= 3) {
                val tagName = "tvWorkTag$workCnt"
                val tagId: Int =
                    resources.getIdentifier(tagName, "id", packageName)
                val tag = findViewById<TextView>(tagId)

                tag.text = newWork
                tag.visibility = View.VISIBLE
            } else {
                Toast.makeText(
                    this@InitProfileActivity,
                    "Maximum 3 tags can be added.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            binding.actvWork.setText("")
        }


        // set on-click listener after entering all data
        binding.btnSetMyProfile.setOnClickListener {
            val name = binding.etName.text;
            val phone = binding.etPhone.text;
            val email = binding.etEmail.text;
            Toast.makeText(this@InitProfileActivity, name, Toast.LENGTH_LONG).show()
            Toast.makeText(this@InitProfileActivity, "Your profile is set.", Toast.LENGTH_SHORT)
                .show()
            // send data
            val intent = Intent(this, InitFriendsActivity::class.java)
            startActivity(
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            )
            finish()
        }
    }

    private fun settingList() {
        val getUserAPI = RetrofitHelper.getInstance().create(GetUserAPI::class.java)
        GlobalScope.launch{
            val result = getUserAPI.getTags(param2 = "Work")
            if (result != null) {
                list.addAll(result.body()!!.toList())
                Log.d("ayush: ", result.body().toString())
            }
        }
    }
}