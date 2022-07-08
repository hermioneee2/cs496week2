package com.example.cs496week2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cs496week2.databinding.ActivityInitProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        if (intent.hasExtra("nameKey")) {
            binding.etName.setText(intent.getStringExtra("nameKey"))
            Log.d("name", intent.getStringExtra("nameKey").toString())
        } else {
            Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
        }

        if (intent.hasExtra("emailKey")) {
            binding.etEmail.setText(intent.getStringExtra("emailKey"))
            Log.d("email", intent.getStringExtra("emailKey").toString())
        } else {
            Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
        }

        // set on-click listener
        binding.btnSetMyProfile.setOnClickListener {
            val name = binding.etName.text;
            val phone = binding.etPhone.text;
            val email = binding.etEmail.text;
            Toast.makeText(this@InitProfileActivity, name, Toast.LENGTH_LONG).show()

            // your code to validate the user_name and password combination
            // and verify the same

        }


    }
}