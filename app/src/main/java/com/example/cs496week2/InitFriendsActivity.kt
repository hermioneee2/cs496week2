package com.example.cs496week2

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cs496week2.databinding.ActivityInitFriendsBinding

class InitFriendsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitFriendsBinding
    //    private val list: MutableList<String>? = null
    private val list: MutableList<String> = mutableListOf()
    private var workCnt: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        //가로 transition 일단 넣어놨는데 별로 안이쁜듯
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
                // set an slide transition
                enterTransition = Slide(Gravity.END)
                exitTransition = Slide(Gravity.START)
            }
        }

        super.onCreate(savedInstanceState)

        binding = ActivityInitFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set on-click listener after entering all data
        binding.btnStartApp.setOnClickListener {
//            val name = binding.etName.text;
//            val phone = binding.etPhone.text;
//            val email = binding.etEmail.text;
//            Toast.makeText(this@InitProfileActivity, name, Toast.LENGTH_LONG).show()
//            Toast.makeText(this@InitProfileActivity, "Your profile is set.", Toast.LENGTH_SHORT).show()
            // send data
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }
    }
}