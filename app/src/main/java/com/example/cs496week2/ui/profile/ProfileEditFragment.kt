package com.example.cs496week2.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cs496week2.R
import com.example.cs496week2.databinding.ActivityItemBinding
import com.example.cs496week2.databinding.ActivityItemEditableBinding
import com.example.cs496week2.databinding.FragmentProfileBinding
import com.example.cs496week2.interfaces.GetUserAPI
import com.example.cs496week2.models.Node
import com.example.cs496week2.models.Property
import com.example.cs496week2.objects.MyProfile
import com.example.cs496week2.objects.RetrofitHelper
import com.example.cs496week2.ui.home.ItemModal
import com.example.cs496week2.ui.home.TagAdapter
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileEditFragment : Fragment() {

    private var _binding: ActivityItemEditableBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val profileViewModel =
//            ViewModelProvider(this).get(ProfileViewModel::class.java)
        var workTagAdapter: TagAdapter? = null;
        var areaTagAdapter: TagAdapter? = null;
        var hobbyTagAdapter: TagAdapter? = null;
        var relationshipTagAdapter: TagAdapter? = null;

        _binding = ActivityItemEditableBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var itemModal = ItemModal(
            MyProfile.userID, MyProfile.name, MyProfile.phone, MyProfile.email,
            ArrayList(MyProfile.work), ArrayList(MyProfile.area),
            ArrayList(MyProfile.hobby), arrayListOf(MyProfile.relationship),
            MyProfile.photoSrc, ArrayList())

        binding.etName.setText(itemModal.name)
        binding.etEmail.setText(itemModal.email)
        binding.etPhone.setText(itemModal.phone)

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

        binding.btnEdit.visibility = View.VISIBLE
//        binding.btnDirect.visibility = View.GONE
//        binding.btnOnBehalfOf.visibility = View.GONE

        binding.actvWork.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line, itemModal!!.workTagList
            )
        )

        binding.actvArea.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line, itemModal!!.areaTagList
            )
        )

        binding.actvHobby.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line, itemModal!!.hobbyTagList
            )
        )

        binding.actvRelationship.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line, itemModal!!.relationshipTagList
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

        Glide.with(this).load(itemModal!!.photoSrc).into(binding.ivProfilePic)

        binding.btnEdit.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.nav_host_fragment_activity_main, ProfileFragment())
            fr?.commit()
            MyProfile.email = binding.etEmail.text.toString()
            MyProfile.work = itemModal.workTagList
            MyProfile.hobby = itemModal.hobbyTagList
            MyProfile.area = itemModal.areaTagList
            MyProfile.relationship = itemModal.relationshipTagList.get(0)
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
                        MyProfile.work, MyProfile.hobby, MyProfile.area, ""
                    )
                )
                if (result != null) {
                    Log.d("ProfileEditFragment", result.body()!!.toString())
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}