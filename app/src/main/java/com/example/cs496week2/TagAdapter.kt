package com.example.cs496week2.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cs496week2.R
import kotlinx.android.synthetic.main.row_items.view.*
import kotlinx.android.synthetic.main.tag.view.*

class TagAdapter(): RecyclerView.Adapter<TagAdapter.TagAdapterVH>() {
    var tagList = ArrayList<String>();

    fun setData(tagList: ArrayList<String>){
        this.tagList = tagList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagAdapterVH {
        return TagAdapterVH(
            LayoutInflater.from(parent.context).inflate(R.layout.tag, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TagAdapterVH, position: Int) {
        holder.tvTag.text = tagList[position]
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

    class TagAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTag = itemView.tvTag
    }
}