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
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener
import com.ucsmonywataungthu.org.Activity.NewsViewActivity
import com.ucsmonywataungthu.org.Activity.VideoFullViewActivity
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.NewsModel

class NewsAdapter (val context: Context, val newsList:List<NewsModel>) : RecyclerView.Adapter<MyHolder6>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder6 {
        return MyHolder6(LayoutInflater.from(parent.context).inflate(R.layout.news_item_row, parent, false))
    }

    override fun getItemCount(): Int {
        return newsList.size

    }

    override fun onBindViewHolder(holder: MyHolder6, position: Int) {
        holder.new_img.setImageResource(newsList.get(position).new_img)
        holder.txt_news_title.text=newsList.get(position).new_title
        holder.txt_news_des.text=newsList.get(position).new_description

        holder.news_layout.setOnClickListener{
            val intent=Intent(context,NewsViewActivity::class.java)
            intent.putExtra("news_image",newsList.get(position).new_img)
            intent.putExtra("news_title",newsList.get(position).new_title)
            intent.putExtra("news_des",newsList.get(position).new_description)
            context.startActivity(intent)
        }

    }
}

class MyHolder6(view: View): RecyclerView.ViewHolder(view) {
    val new_img=view.findViewById<ImageView>(R.id.news_img)
    val txt_news_time=view.findViewById<TextView>(R.id.txt_news_time)
    val txt_news_title=view.findViewById<TextView>(R.id.txt_news_title)
    val txt_news_des=view.findViewById<TextView>(R.id.txt_news_description)
    val news_layout=view.findViewById<LinearLayout>(R.id.news_layout)
}
