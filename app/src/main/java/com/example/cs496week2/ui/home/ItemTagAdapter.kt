package com.example.cs496week2.ui.home

import android.content.ClipData
import android.content.Intent
import android.nfc.Tag
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cs496week2.R
import kotlinx.android.synthetic.main.activity_item.view.*
import kotlinx.android.synthetic.main.row_items_tag.view.tvTag
import kotlinx.android.synthetic.main.row_items_tag.view.rvProfile

class ItemTagAdapter(    var clickedItem: ProfileCircleAdapter.ClickedItem
): RecyclerView.Adapter<ItemTagAdapter.ItemTagAdapterVH>(){
    var itemTagModalList = ArrayList<ItemTagModal>();
    var profileCircleAdapter: ProfileCircleAdapter? = null;

    fun setData(itemTagModalList: ArrayList<ItemTagModal>){
        this.itemTagModalList = itemTagModalList

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTagAdapterVH {
        return ItemTagAdapterVH(
            LayoutInflater.from(parent.context).inflate(R.layout.row_items_tag, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemTagAdapterVH, position: Int) {
        var itemTagModal = itemTagModalList[position]

//        Glide.with(holder.ivProfilePic).load(itemModal.photoSrc).into(holder.ivProfilePic.ivProfilePic)

        holder.tvTag.text = itemTagModal.tag

        holder.rvProfile.setHasFixedSize(true)
        profileCircleAdapter = ProfileCircleAdapter(clickedItem)
        profileCircleAdapter!!.setData(itemTagModal.profileList)
        holder.rvProfile.adapter = profileCircleAdapter;
    }

    override fun getItemCount(): Int {
        return itemTagModalList.size
    }

    class ItemTagAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTag = itemView.tvTag
        var rvProfile = itemView.rvProfile
    }

//    override fun clickedItem(itemProfile: ItemModal) {
//        var itemModal1 = itemProfile;
//
//        val intent = Intent(activity, ItemActivity::class.java)
//        intent.putExtra("data", itemModal1)
//        requireActivity.startActivity(intent)
//    }

}