package com.example.cs496week2.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cs496week2.R
import com.example.cs496week2.ui.home.ItemAdapter
import com.example.cs496week2.ui.home.ItemModal
import com.example.cs496week2.ui.home.TagAdapter
import kotlinx.android.synthetic.main.row_items.view.*

class NotificationAdapter(): RecyclerView.Adapter<NotificationAdapter.NotificationAdapterVH>() {
    var itemModalList = ArrayList<ArrayList<ItemModal>>();

    fun setData(itemModalList: ArrayList<ArrayList<ItemModal>>){
        this.itemModalList = itemModalList

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationAdapterVH {
        return NotificationAdapterVH(
            LayoutInflater.from(parent.context).inflate(R.layout.row_notifications, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotificationAdapterVH, position: Int) {
        var threeItemModal = itemModalList[position]

        Glide.with(holder.ivProfilePic0).load(threeItemModal[0].photoSrc).into(holder.ivProfilePic0.ivProfilePic0)
        holder.name0.text = threeItemModal[0].name
    }

    override fun getItemCount(): Int {
        return itemModalList.size
    }

    class NotificationAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivProfilePic0 = itemView.ivProfilePic0
        var ivProfilePic1 = itemView.ivProfilePic1
        var ivProfilePic2 = itemView.ivProfilePic2
        var name0 = itemView.tvName0
        var name1 = itemView.tvName1
        var name2 = itemView.tvName2
    }


}