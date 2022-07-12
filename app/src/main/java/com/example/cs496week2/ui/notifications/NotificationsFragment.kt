package com.example.cs496week2.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496week2.databinding.FragmentNotificationsBinding
import com.example.cs496week2.ui.home.ItemAdapter
import com.example.cs496week2.ui.home.ItemModal

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var dummyTagList = arrayListOf(
        "삼성전자", "LG전자", "네이버"
    )

    var itemListModal = arrayListOf(
        ItemModal("11", "이혜림", "010-1463-5364", "hermioneee2@gmail.com",dummyTagList, dummyTagList, dummyTagList, dummyTagList,"https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", dummyTagList),
        ItemModal("12", "윤태영", "010-1233-4522", "tythankyou@gmail.com", dummyTagList, dummyTagList, dummyTagList, dummyTagList,"https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", dummyTagList),
        ItemModal("13", "윤하나", "010-1735-6275", "lollipop@gmail.com", dummyTagList, dummyTagList, dummyTagList, dummyTagList,"https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", dummyTagList),
    )

    var itemModalList = arrayListOf(
        itemListModal,
        itemListModal,
        itemListModal
    )

    var notificationAdapter: NotificationAdapter? = null;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerView.layoutManager = LinearLayoutManager(context);
        binding.recyclerView.setHasFixedSize(true)

        notificationAdapter = NotificationAdapter();
        notificationAdapter!!.setData(itemModalList)

        binding.recyclerView.adapter = notificationAdapter


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}