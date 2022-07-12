package com.example.cs496week2.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cs496week2.R
import kotlinx.android.synthetic.main.profile_circle.view.*

class ProfileCircleAdapter(): RecyclerView.Adapter<ProfileCircleAdapter.ProfileCircleAdapterVH>() {
    var profileList = ArrayList<ItemModal>();

    fun setData(profileList: ArrayList<ItemModal>){
        this.profileList = profileList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileCircleAdapterVH {
        return ProfileCircleAdapterVH(
            LayoutInflater.from(parent.context).inflate(R.layout.profile_circle, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileCircleAdapterVH, position: Int) {
        holder.tvName.text = profileList[position].name
        Glide.with(holder.ivProfilePic).load(profileList[position].photoSrc).into(holder.ivProfilePic.ivProfilePic)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    class ProfileCircleAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivProfilePic = itemView.ivProfilePic
        var tvName = itemView.tvName
    }
}