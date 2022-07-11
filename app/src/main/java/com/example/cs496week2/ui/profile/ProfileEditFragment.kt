package com.example.cs496week2.ui.profile

import android.os.Bundle
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
import com.example.cs496week2.ui.home.ItemModal
import com.example.cs496week2.ui.home.TagAdapter
import kotlinx.android.synthetic.main.activity_item.*

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

        //TODO: Call my info from MYPROFILE
        var dummyTagList = arrayListOf(
            "삼성전자", "LG전자", "네이버"
        )
        var itemModal = ItemModal("11", "이혜림", "010-1463-5364", "hermioneee2@gmail.com", dummyTagList, dummyTagList, dummyTagList, dummyTagList,"https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", dummyTagList)


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




        Glide.with(this).load(itemModal!!.photoSrc).into(binding.ivProfilePic)

        binding.btnEdit.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.nav_host_fragment_activity_main, ProfileFragment())
            fr?.commit()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}