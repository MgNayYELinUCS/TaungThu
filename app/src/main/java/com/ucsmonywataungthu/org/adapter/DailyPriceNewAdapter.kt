package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.DailyPricePhoto
import com.ucsmonywataungthu.org.model.HomeModel

class DailyPriceNewAdapter (val context: Context, val dailyPriceList:List<DailyPricePhoto>) : RecyclerView.Adapter<DailyPrice>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyPrice {
        return DailyPrice(LayoutInflater.from(parent.context).inflate(R.layout.daily_price_new_row, parent, false))
    }

    override fun getItemCount(): Int {
        return dailyPriceList.size

    }

    override fun onBindViewHolder(holder: DailyPrice, position: Int) {
        holder.daily_price_date.text=dailyPriceList.get(position).daily_price_date
       // holder.daily_price_image.setImageResource(dailyPriceList.get(position).daily_price_photo)
        Glide.with(context!!)
            .load(APIInitiate.PIC_URL+dailyPriceList.get(position).daily_price_photo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.daily_price_image)
    }
}

class DailyPrice(view: View): RecyclerView.ViewHolder(view) {
    val daily_price_date=view.findViewById<TextView>(R.id.daily_price_date)
    val daily_price_image=view.findViewById<ImageView>(R.id.daily_price_image)
}