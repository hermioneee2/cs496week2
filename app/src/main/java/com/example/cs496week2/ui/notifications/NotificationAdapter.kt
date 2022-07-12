package com.example.cs496week2.ui.notifications

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cs496week2.R
import com.example.cs496week2.ui.home.ItemAdapter
import com.example.cs496week2.ui.home.ItemModal
import com.example.cs496week2.ui.home.ProfileCircleAdapter
import com.example.cs496week2.ui.home.TagAdapter
import kotlinx.android.synthetic.main.row_notifications.view.*

class NotificationAdapter(
    var context: Context
): RecyclerView.Adapter<NotificationAdapter.NotificationAdapterVH>() {
    var itemModalList = ArrayList<ArrayList<ItemModal>>();

    fun setData(itemModalList: ArrayList<ArrayList<ItemModal>>){
        this.itemModalList = itemModalList

        notifyDataSetChanged()
    }

//    interface ClickedItem{
//        fun clickedItem(itemProfile: ItemModal)
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationAdapterVH {
        return NotificationAdapterVH(
            LayoutInflater.from(parent.context).inflate(R.layout.row_notifications, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotificationAdapterVH, position: Int) {
        var threeItemModal = itemModalList[position]

        Glide.with(holder.ivProfilePic0).load(threeItemModal[0].photoSrc).into(holder.ivProfilePic0.ivProfilePic0)
//        holder.name0.text = threeItemModal[0].name

        Glide.with(holder.ivProfilePic1).load(threeItemModal[1].photoSrc).into(holder.ivProfilePic1.ivProfilePic1)
//        holder.name1.text = threeItemModal[1].name
        var mydesc = "${threeItemModal[0].name}님이 ${threeItemModal[1].name}님을 알고 싶어해요.\n대신 연결해줄래요?"
        holder.description.text = mydesc

        holder.btnConnect.setOnClickListener{
            // send SMS directly
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + threeItemModal[0].phone))
            intent.putExtra("sms_body", "[한다리건너]\n 안녕하세요, ${threeItemModal[0].name}님! 제 지인인 ${threeItemModal[1].name}님이 ${threeItemModal[0].name}님을 알고 싶어해요. 그의 연락처는 ${threeItemModal[1].phone}이에요.")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemModalList.size
    }

    class NotificationAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivProfilePic0 = itemView.ivProfilePic0
        var ivProfilePic1 = itemView.ivProfilePic1
//        var name0 = itemView.tvName0
//        var name1 = itemView.tvName1
        var description = itemView.tvDescription
        var btnConnect = itemView.btnConnect

    }


}