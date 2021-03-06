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
import com.example.cs496week2.interfaces.GetUserAPI
import com.example.cs496week2.objects.MyProfile
import com.example.cs496week2.objects.RetrofitHelper
import com.example.cs496week2.ui.home.ItemAdapter
import com.example.cs496week2.ui.home.ItemModal
import com.example.cs496week2.ui.home.ProfileCircleAdapter
import com.example.cs496week2.ui.home.TagAdapter
import kotlinx.android.synthetic.main.row_notifications.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        var mydesc = "${threeItemModal[0].name}?????? ${threeItemModal[1].name}?????? ?????? ????????????.\n?????? ???????????????????"
        holder.description.text = mydesc

        holder.btnConnect.setOnClickListener{
            val getUserAPI = RetrofitHelper.getInstance().create(GetUserAPI::class.java)
            GlobalScope.launch {
                val result = getUserAPI.getUserList(
                    param1 = threeItemModal[0].userID,
                    param2 = threeItemModal[1].userID,
                    body = listOf("delete")
                )
                withContext(Dispatchers.Main) {
                    Log.d("ItemActivity remove temp link: ", result.body().toString())
                    notifyDataSetChanged()
                }
            }

            // send SMS directly
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + threeItemModal[0].phone))
            intent.putExtra("sms_body", "[???????????????]\n ???????????????, ${threeItemModal[1].name}???! ??? ????????? ${threeItemModal[0].name}?????? ${threeItemModal[1].name}?????? ?????? ????????????. ?????? ???????????? ${threeItemModal[0].phone}?????????.")
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