package com.example.cs496week2.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.cs496week2.R
import com.example.cs496week2.databinding.ActivityItemBinding
import com.example.cs496week2.databinding.FragmentProfileBinding
import com.example.cs496week2.objects.MyProfile
import com.example.cs496week2.ui.home.ItemModal
import com.example.cs496week2.ui.home.TagAdapter
import kotlinx.android.synthetic.main.activity_item.*

class ProfileFragment : Fragment() {

    private var _binding: ActivityItemBinding? = null

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

        _binding = ActivityItemBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var itemModal = ItemModal(
            MyProfile.userID, MyProfile.name, MyProfile.phone, MyProfile.email,
            ArrayList(MyProfile.work), ArrayList(MyProfile.area),
            ArrayList(MyProfile.hobby), arrayListOf(MyProfile.relationship),
            MyProfile.photoSrc, ArrayList())

        binding.tvName.text = itemModal.name
        binding.tvEmail.text = itemModal.email
        binding.tvPhone.text = itemModal.phone

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
        binding.btnDirect.visibility = View.GONE
        binding.btnOnBehalfOf.visibility = View.GONE

        Glide.with(this).load(itemModal!!.photoSrc).into(binding.ivProfilePic)

//        // set on-click listener for editing profile
//        binding.btnEdit.setOnClickListener {
//            activity.getSupportFragmentManager().beginTransaction()
//                .replace(R.id.constraintContainer, ProfileEditFragment)
//                .commit()
//
//            // as per defined in your FragmentContainerView
//            val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
//            val navController = navHostFragment.navController
//
//            // Navigate using the IDs you defined in your Nav Graph
//            navController.navigate(R.id.blankFragment)
//        }

        binding.btnEdit.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.nav_host_fragment_activity_main, ProfileEditFragment())
            fr?.commit()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}