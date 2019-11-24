package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Activity.NotificationViewActivity
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.InfoModel

class SettingAdapter (val context: Context, val infoList:List<InfoModel>) : RecyclerView.Adapter<MyHolder8>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder8 {
        return MyHolder8(LayoutInflater.from(parent.context).inflate(R.layout.info_layout_row, parent, false))
    }

    override fun getItemCount(): Int {
        return infoList.size

    }

    override fun onBindViewHolder(holder: MyHolder8, position: Int) {
        holder.info_image.setImageResource(infoList.get(position).info_image)
        holder.info_title.text=infoList.get(position).info_title

        holder.info_image.setOnClickListener{
            val intent= Intent(context, NotificationViewActivity::class.java)
            intent.putExtra("news_image",infoList.get(position).info_image)
            intent.putExtra("news_title",infoList.get(position).info_title)
            context.startActivity(intent)
        }

    }
}

class MyHolder8(view: View): RecyclerView.ViewHolder(view) {
    val info_image=view.findViewById<ImageView>(R.id.info_image)
    val info_title=view.findViewById<TextView>(R.id.info_title)
}
