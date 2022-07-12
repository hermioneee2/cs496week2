package com.example.cs496week2.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496week2.databinding.FragmentNotificationsBinding
import com.example.cs496week2.interfaces.GetUserAPI
import com.example.cs496week2.models.Node
import com.example.cs496week2.models.Property
import com.example.cs496week2.objects.MyProfile
import com.example.cs496week2.objects.RetrofitHelper
import com.example.cs496week2.ui.home.ItemAdapter
import com.example.cs496week2.ui.home.ItemModal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var itemListModal = ArrayList<ItemModal>()

    var itemModalList = ArrayList<ArrayList<ItemModal>>()

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

        val getUserAPI = RetrofitHelper.getInstance().create(GetUserAPI::class.java)
        GlobalScope.launch {
            val result = getUserAPI.getTempLinks(id = MyProfile.userID)
            Log.d("Taeyoung Not", result.body()!!.toString())
            result.body()!!.forEach {nodelist ->
                val node1 = nodelist[0]
                val node2 = nodelist[1]
                val result1 = getUserAPI.getUser(id = node1.properties.userID)
                val result2 = getUserAPI.getUser(id = node2.properties.userID)
                val actualNode1 = result1.body()!!
                val actualNode2 = result2.body()!!
                itemModalList.add(
                    arrayListOf(
                        ItemModal(
                            actualNode1.properties.userID, actualNode1.properties.name,
                            actualNode1.properties.phone, actualNode1.properties.email,
                            ArrayList(actualNode1.work), ArrayList(actualNode1.area),
                            ArrayList(actualNode1.hobby), ArrayList(listOf(actualNode1.relationship)),
                            actualNode1.properties.photoSrc, ArrayList()
                        ),
                        ItemModal(
                            actualNode2.properties.userID, actualNode2.properties.name,
                            actualNode2.properties.phone, actualNode2.properties.email,
                            ArrayList(actualNode2.work), ArrayList(actualNode2.area),
                            ArrayList(actualNode2.hobby), ArrayList(listOf(actualNode2.relationship)),
                            actualNode2.properties.photoSrc, ArrayList())
                    )
                )
            }

            withContext(Dispatchers.Main) {
                binding.recyclerView.layoutManager = LinearLayoutManager(context);
                binding.recyclerView.setHasFixedSize(true)

                notificationAdapter = NotificationAdapter(requireContext());
                notificationAdapter!!.setData(itemModalList)

                binding.recyclerView.adapter = notificationAdapter
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}