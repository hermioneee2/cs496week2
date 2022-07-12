package com.example.cs496week2.ui.home

import android.content.ClipData
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496week2.InitProfileActivity
import com.example.cs496week2.R
import com.example.cs496week2.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), ItemAdapter.ClickedItem, ProfileCircleAdapter.ClickedItem  {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var dummyTagList = arrayListOf(
        "삼성전자", "LG전자", "네이버"
    )

    //한다리+한다리건너 지인 정보 모두 리턴
    var itemListModal = arrayListOf(
        ItemModal("11", "이혜림", "010-1463-5364", "hermioneee2@gmail.com", dummyTagList, dummyTagList, dummyTagList, dummyTagList,"https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", dummyTagList),
        ItemModal("12", "윤태영", "010-1233-4522", "tythankyou@gmail.com", dummyTagList, dummyTagList, dummyTagList, dummyTagList,"https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", dummyTagList),
        ItemModal("13", "윤하나", "010-1735-6275", "lollipop@gmail.com", dummyTagList, dummyTagList, dummyTagList, dummyTagList,"https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", dummyTagList),
        ItemModal("11", "김민수", "010-1463-5364", "minsu@gmail.com", dummyTagList, dummyTagList, dummyTagList, dummyTagList,"https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", dummyTagList),
        ItemModal("12", "이하연", "010-1233-4522", "hiyeon@gmail.com", dummyTagList, dummyTagList, dummyTagList, dummyTagList,"https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", dummyTagList),
        ItemModal("13", "박태균", "010-1735-6275", "tk4233@gmail.com", dummyTagList, dummyTagList, dummyTagList, dummyTagList,"https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", dummyTagList),
//        ItemModal("https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", "Apple", dummyTagList),
//        ItemModal("https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", "Banana", dummyTagList),
//        ItemModal("https://k.kakaocdn.net/dn/bER0sf/btry33hyOgb/Y2R8LMaVEhbgcvq5KCK110/img_110x110.jpg", "Orange", dummyTagList)

    )

    var itemModalList = ArrayList<ItemModal>();

    var itemAdapter: ItemAdapter? = null;
    var itemTagAdapter: ItemTagAdapter? = null;

    //DUMMY FOR TAGS
    var itemTagModalList = arrayListOf(
        ItemTagModal("삼성전자", itemListModal),
        ItemTagModal("LG", itemListModal)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        for (item in itemListModal){
            itemModalList.add(item)
        }

        //SEARCH RESULT
        binding.rvSearchResult.layoutManager = LinearLayoutManager(context);
        binding.rvSearchResult.setHasFixedSize(true)

        itemAdapter = ItemAdapter(this);
        itemAdapter!!.setData(itemModalList)

        binding.rvSearchResult.adapter = itemAdapter

        //DEFAULT BY TAG
        binding.rvDefault.layoutManager = LinearLayoutManager(context);
        binding.rvDefault.setHasFixedSize(true)

        itemTagAdapter = ItemTagAdapter(this);
        itemTagAdapter!!.setData(itemTagModalList)

        binding.rvDefault.adapter = itemTagAdapter

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

    override fun clickedItem2(itemProfile: ItemModal) {
        var itemModal1 = itemProfile;

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
                if (p0 == null || p0.isEmpty()){
                    _binding!!.rvSearchResult.visibility = View.GONE
                    _binding!!.rvDefault.visibility = View.VISIBLE
                    _binding!!.explanation.visibility = View.VISIBLE

                } else {
                    _binding!!.rvSearchResult.visibility = View.VISIBLE
                    _binding!!.rvDefault.visibility = View.GONE
                    _binding!!.explanation.visibility = View.GONE
                }
                itemAdapter!!.filter.filter(p0);

                return true
            }

        })

        return;

    }
}