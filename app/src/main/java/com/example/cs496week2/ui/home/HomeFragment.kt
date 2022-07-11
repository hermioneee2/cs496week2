package com.example.cs496week2.ui.home

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496week2.InitProfileActivity
import com.example.cs496week2.R
import com.example.cs496week2.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), ItemAdapter.ClickedItem {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var itemListModal = arrayOf(
        ItemModal("https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", "Apple", "apple desc"),
        ItemModal("https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", "Banana", "Banana desc"),
        ItemModal("https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", "Orange", "Orange desc")


    )

    var itemModalList = ArrayList<ItemModal>();

    var itemAdapter: ItemAdapter? = null;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        for (item in itemListModal){
            itemModalList.add(item)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context);
        binding.recyclerView.setHasFixedSize(true)

        itemAdapter = ItemAdapter(this);
        itemAdapter!!.setData(itemModalList)

        binding.recyclerView.adapter = itemAdapter

        setHasOptionsMenu(true);

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clickedItem(itemModal: ItemModal) {
        var itemModal1 = itemModal;
//        Log.e("TAG", "==> "+itemModal1.name)

        var name = itemModal1.name;

        val intent = Intent(activity, ItemActivity::class.java)
        intent.putExtra("data", itemModal1)
        startActivity(intent)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        var menuItem = menu.findItem(R.id.searchView);

        var searchView = menuItem.actionView as SearchView;

        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
//                Log.e("TAG", "=> $p0")
                itemAdapter!!.filter.filter(p0);
                return true
            }

        })

        return;

    }
}