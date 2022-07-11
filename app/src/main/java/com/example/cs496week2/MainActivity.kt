package com.example.cs496week2

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cs496week2.databinding.ActivityMainBinding
import com.example.cs496week2.interfaces.GetUserAPI
import com.example.cs496week2.objects.MyProfile
import com.example.cs496week2.objects.RetrofitHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = getNamePhoneDetails()
        val getUserAPI = RetrofitHelper.getInstance().create(GetUserAPI::class.java)
        GlobalScope.launch{
            val result = getUserAPI.getUserList(
                param1 = "getPhoneList", param2 = "aa", body = list!!.toList()
            )
            if (result != null) {
                MyProfile.friendList = result.body()!!.map { node -> node.properties.userID }
            }
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        //for Kakao login setting
//        val keyHash = Utility.getKeyHash(this)//onCreate 안에 입력해주자
//        Log.d("Hash", keyHash)
    }

    companion object {
        const val PERMISSIONS_REQUEST_READ_CONTACTS = 100
    }

    @SuppressLint("Range")
    fun getNamePhoneDetails(): MutableList<String>? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
            //callback onRequestPermissionsResult
        }
        val names: MutableList<String> = mutableListOf()
        val cr = contentResolver
        val cur = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            null, null, null
        )
        if (cur!!.count > 0) {
            while (cur.moveToNext()) {
                val number =
                    cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace("-", "")
                names.add(number)
            }
        }
        return names.distinctBy { it }.toMutableList()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(
                    this,
                    "Permission must be granted in order to display contacts information",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}