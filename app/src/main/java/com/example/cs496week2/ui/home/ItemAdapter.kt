package com.example.cs496week2.ui.home

import android.content.ClipData
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
import kotlinx.android.synthetic.main.row_items.view.*
import kotlinx.android.synthetic.main.row_items.view.ivProfilePic
import kotlinx.android.synthetic.main.row_items.view.tvName

class ItemAdapter(
    var clickedItem: ClickedItem
): RecyclerView.Adapter<ItemAdapter.ItemAdapterVH>(), Filterable {
    var itemModalList = ArrayList<ItemModal>();
    var itemModalListFilter = ArrayList<ItemModal>();
    var tagAdapter: TagAdapter? = null;

    fun setData(itemModalList: ArrayList<ItemModal>){
        this.itemModalList = itemModalList
        this.itemModalListFilter = itemModalList

        notifyDataSetChanged()
    }

    interface ClickedItem{
        fun clickedItem(itemModal: ItemModal)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterVH {
        return ItemAdapterVH(
            LayoutInflater.from(parent.context).inflate(R.layout.row_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemAdapterVH, position: Int) {
        var itemModal = itemModalList[position]

//        holder.imageView.setImageResource(itemModal.imageSrc)
        Glide.with(holder.ivProfilePic).load(itemModal.photoSrc).into(holder.ivProfilePic.ivProfilePic)

        holder.name.text = itemModal.name

        holder.rvTag.setHasFixedSize(true)
        tagAdapter = TagAdapter()
        tagAdapter!!.setData(itemModal.tagList)

        holder.rvTag.adapter = tagAdapter;

        holder.itemView.setOnClickListener{
            clickedItem.clickedItem(itemModal)
        }
    }

    override fun getItemCount(): Int {
        return itemModalList.size
    }

    class ItemAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivProfilePic = itemView.ivProfilePic
        var name = itemView.tvName
        var rvTag = itemView.rvTag
    }

    override fun getFilter(): Filter {
//        var filter = Filter()

        return object: Filter(){
            override fun performFiltering(charsequence: CharSequence?): FilterResults {
                var filterResults = FilterResults();
                if (charsequence == null || charsequence.isEmpty()){
                    filterResults.count = itemModalListFilter.size
                    filterResults.values = itemModalListFilter;
                } else {
                    var searchChr: String = charsequence.toString().toLowerCase();
                    var itemModal = ArrayList<ItemModal>();
                    for (items in itemModalListFilter) {
                        if (items.name.toLowerCase().contains(searchChr)) {
                            itemModal.add(items)
                        } else {
                            for (tags in items.tagList) {
                                if (tags.contains(searchChr)) {
                                    itemModal.add(items)
                                }
                            }
                        }

                    }

                    itemModal = ArrayList(itemModal.distinctBy { item -> item.userID })
                    filterResults.count = itemModal.size
                    filterResults.values = itemModal
                }

                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                itemModalList = p1!!.values as ArrayList<ItemModal>
                notifyDataSetChanged();
            }

        }
    }

}