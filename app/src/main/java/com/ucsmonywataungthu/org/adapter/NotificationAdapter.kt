package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Activity.NewsViewActivity
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.NotificationModel

class NotificationAdapter (val context: Context, val notificationList:List<NotificationModel>) : RecyclerView.Adapter<MyHolder0>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder0 {
        return MyHolder0(LayoutInflater.from(parent.context).inflate(R.layout.news_item_row, parent, false))
    }

    override fun getItemCount(): Int {
        return notificationList.size

    }

    override fun onBindViewHolder(holder: MyHolder0, position: Int) {
        holder.new_img.setImageResource(R.mipmap.plant)
        holder.txt_news_title.text=notificationList.get(position).notification_title
        holder.txt_news_des.text=notificationList.get(position).notification_description

        holder.news_layout.setOnClickListener{
            val intent= Intent(context, NewsViewActivity::class.java)
            //intent.putExtra("news_image",notificationList.get(position).new_img)
            intent.putExtra("news_title",notificationList.get(position).notification_title)
            intent.putExtra("news_des",notificationList.get(position).notification_description)
            context.startActivity(intent)
        }

    }
}

class MyHolder0(view: View): RecyclerView.ViewHolder(view) {
    val new_img=view.findViewById<ImageView>(R.id.news_img)
    val txt_news_time=view.findViewById<TextView>(R.id.txt_news_time)
    val txt_news_title=view.findViewById<TextView>(R.id.txt_news_title)
    val txt_news_des=view.findViewById<TextView>(R.id.txt_news_description)
    val news_layout=view.findViewById<LinearLayout>(R.id.news_layout)
}
