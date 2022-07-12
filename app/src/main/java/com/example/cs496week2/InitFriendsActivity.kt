package com.example.cs496week2

import android.R
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496week2.databinding.ActivityInitFriendsBinding
import com.example.cs496week2.interfaces.GetUserAPI
import com.example.cs496week2.models.Node
import com.example.cs496week2.models.Property
import com.example.cs496week2.objects.MyProfile
import com.example.cs496week2.objects.RetrofitHelper
import com.example.cs496week2.ui.home.ItemAdapter
import com.example.cs496week2.ui.home.ItemModal
import com.example.cs496week2.ui.home.TagAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InitFriendsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitFriendsBinding
    //    private val list: MutableList<String>? = null
    private var list: MutableList<String> = mutableListOf()
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

        var itemModal = ItemModal(
            MyProfile.userID, MyProfile.name, MyProfile.phone, MyProfile.email,
            ArrayList(MyProfile.work), ArrayList(MyProfile.area),
            ArrayList(MyProfile.hobby), ArrayList(),
            MyProfile.photoSrc, ArrayList())
        var workTagAdapter: TagAdapter? = null
        var areaTagAdapter: TagAdapter? = null
        var hobbyTagAdapter: TagAdapter? = null
        var relationshipTagAdapter: TagAdapter? = null

        workTagAdapter = TagAdapter()
        binding.rvWorkTag.setHasFixedSize(true)
        workTagAdapter!!.setData(itemModal!!.workTagList)
        binding.rvWorkTag.adapter = workTagAdapter;

        areaTagAdapter = TagAdapter()
        binding.rvAreaTag.setHasFixedSize(true)
        areaTagAdapter!!.setData(itemModal!!.areaTagList)
        binding.rvAreaTag.adapter = areaTagAdapter;

        hobbyTagAdapter = TagAdapter()
        binding.rvHobbyTag.setHasFixedSize(true)
        hobbyTagAdapter!!.setData(itemModal!!.hobbyTagList)
        binding.rvHobbyTag.adapter = hobbyTagAdapter;

        relationshipTagAdapter = TagAdapter()
        binding.rvRelationshipTag.setHasFixedSize(true)
        relationshipTagAdapter!!.setData(itemModal!!.relationshipTagList)
        binding.rvRelationshipTag.adapter = relationshipTagAdapter;

        settingList("Work")
        settingList("Area")
        settingList("Hobby")
        binding.actvWork.setAdapter(
            ArrayAdapter(
                this,
                R.layout.simple_dropdown_item_1line, list
            )
        )
        binding.actvArea.setAdapter(
            ArrayAdapter(
                this,
                R.layout.simple_dropdown_item_1line, list
            )
        )
        binding.actvHobby.setAdapter(
            ArrayAdapter(
                this,
                R.layout.simple_dropdown_item_1line, list
            )
        )

        binding.actvRelationship.setAdapter(
            ArrayAdapter(
                this,
                R.layout.simple_dropdown_item_1line, list
            )
        )

        // set on-click listener for adding tags //WORK
        binding.btnWork.setOnClickListener {
            val newWork = binding.actvWork.text;
            itemModal!!.workTagList.add(newWork.toString())
            binding.actvWork.setText("")
            binding.rvWorkTag.adapter!!.notifyDataSetChanged()
        }
        binding.btnArea.setOnClickListener {
            val newArea = binding.actvArea.text;
            itemModal!!.areaTagList.add(newArea.toString())
            binding.actvArea.setText("")
            binding.rvAreaTag.adapter!!.notifyDataSetChanged()
        }
        binding.btnHobby.setOnClickListener {
            val newHobby = binding.actvHobby.text;
            itemModal!!.hobbyTagList.add(newHobby.toString())
            binding.actvHobby.setText("")
            binding.rvHobbyTag.adapter!!.notifyDataSetChanged()
        }
        binding.btnRelationship.setOnClickListener {
            val newWork = binding.actvRelationship.text;
            itemModal!!.relationshipTagList.add(newWork.toString())
            binding.actvRelationship.setText("")
            binding.rvRelationshipTag.adapter!!.notifyDataSetChanged()
        }

        // set on-click listener after entering all data
        binding.btnStartApp.setOnClickListener {
            MyProfile.work = itemModal.workTagList
            MyProfile.hobby = itemModal.hobbyTagList
            MyProfile.area = itemModal.areaTagList
            MyProfile.relationship = itemModal.relationshipTagList.get(0)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))

            val getUserAPI = RetrofitHelper.getInstance().create(GetUserAPI::class.java)
            GlobalScope.launch{
                val result = getUserAPI.postUser(
                    id = MyProfile.userID,
                    body = Node(
                        0,
                        listOf("Person"),
                        Property(
                            MyProfile.userID, MyProfile.name, MyProfile.phone, MyProfile.email, MyProfile.photoSrc
                        ),
                        MyProfile.work, MyProfile.hobby, MyProfile.area, MyProfile.relationship
                    )
                )
                if (result != null) {
                    Log.d("Taeyoung", result.body()!!.toString())
                }
                finish()
            }
        }
    }

    private fun settingList(param : String) {
        val getUserAPI = RetrofitHelper.getInstance().create(GetUserAPI::class.java)
        GlobalScope.launch{
            val result = getUserAPI.getTags(param2 = param)
            if (result != null) {
                list.addAll(result.body()!!.toList())
                Log.d("ayush: ", result.body().toString())
            }
        }
    }
}