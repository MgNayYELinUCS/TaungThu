package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ucsmonywataungthu.org.Activity.NewsDetailActivity
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.News

class NewsAdapter(val context: Context, val newList:List<News>) : RecyclerView.Adapter<NewViewModel>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewModel {
        return  NewViewModel(LayoutInflater.from(parent.context).inflate(R.layout.news_list_row, parent, false))

    }

    override fun getItemCount(): Int {
        return newList!!.size
    }

    override fun onBindViewHolder(holder: NewViewModel, position: Int) {
        holder.tv_news_time.text=newList.get(position).created_at.toString()
        holder.tv_news_title.text=newList.get(position).news_title

        Glide.with(context!!)
            .load(APIInitiate.PIC_URL+newList.get(position).news_photo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.iv_news_image)

        holder.new_row.setOnClickListener {
            val intent=Intent(context,NewsDetailActivity::class.java)
            intent.putExtra("title",newList.get(position).news_title)
            intent.putExtra("desc",newList.get(position).news_description)
            intent.putExtra("image",newList.get(position).news_photo)
            intent.putExtra("time",newList.get(position).created_at.toString())
            context.startActivity(intent)


        }
    }
}

class NewViewModel (view: View): RecyclerView.ViewHolder(view) {
val iv_news_image:ImageView=view.findViewById(R.id.iv_news_image)
    val  tv_news_title:TextView=view.findViewById(R.id.tv_news_title)
    val tv_news_time:TextView=view.findViewById(R.id.tv_news_created_time)
    val new_row:ConstraintLayout=view.findViewById(R.id.news_row)


}
