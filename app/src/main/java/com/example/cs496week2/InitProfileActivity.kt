package com.example.cs496week2

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cs496week2.databinding.ActivityInitProfileBinding


class InitProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitProfileBinding
    // creating array adapter for listview
    lateinit var listAdapter: ArrayAdapter<String>
    // creating array list for listview
    lateinit var programmingLanguagesList: ArrayList<String>;

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

        // Verify the action and get the query
        // on below line we are


        // initializing list and adding data to list
        programmingLanguagesList = ArrayList()
        programmingLanguagesList.add("C")
        programmingLanguagesList.add("C#")
        programmingLanguagesList.add("Java")
        programmingLanguagesList.add("Javascript")
        programmingLanguagesList.add("Python")
        programmingLanguagesList.add("Dart")
        programmingLanguagesList.add("Kotlin")
        programmingLanguagesList.add("Typescript")

        // initializing list adapter and setting layout
        // for each list view item and adding array list to it.
        listAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            programmingLanguagesList
        )

        // on below line setting list
        // adapter to our list view.
        binding.lvWorkOption.adapter = listAdapter

        // on below line we are adding on query
        // listener for our search view.
        binding.svWork.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // on below line we are checking
                // if query exist or not.
                if (programmingLanguagesList.contains(query)) {
                    // if query exist within list we
                    // are filtering our list adapter.
                    listAdapter.filter.filter(query)
                } else {
                    // if query is not present we are displaying
                    // a toast message as no  data found..
                    Toast.makeText(this@InitProfileActivity, "No Language found..", Toast.LENGTH_LONG)
                        .show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // if query text is change in that case we
                // are filtering our adapter with
                // new text on below line.
                listAdapter.filter.filter(newText)
                return false
            }
        })

        // set on-click listener after entering all data
        binding.btnSetMyProfile.setOnClickListener {
            val name = binding.etName.text;
            val phone = binding.etPhone.text;
            val email = binding.etEmail.text;
            Toast.makeText(this@InitProfileActivity, name, Toast.LENGTH_LONG).show()
            // send data
        }
    }


}