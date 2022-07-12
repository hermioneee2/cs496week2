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

        // set on-click listener after entering all data
        binding.btnSetMyProfile.setOnClickListener {
            MyProfile.name = binding.etName.text.toString()
            MyProfile.phone = binding.etPhone.text.toString()
            MyProfile.email = binding.etEmail.text.toString()
            Toast.makeText(this@InitProfileActivity, MyProfile.name, Toast.LENGTH_LONG).show()
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
}