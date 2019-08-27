package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.HomeModel

class DailyPriceNewAdapter (val context: Context, val cropList:List<HomeModel>) : RecyclerView.Adapter<DailyPrice>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyPrice {
        return DailyPrice(LayoutInflater.from(parent.context).inflate(R.layout.daily_price_new_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cropList.size

    }

    override fun onBindViewHolder(holder: DailyPrice, position: Int) {
        holder.daily_price_date.text=cropList.get(position).name
        holder.daily_price_image.setImageResource(cropList.get(position).img)

    }
}

class DailyPrice(view: View): RecyclerView.ViewHolder(view) {
    val daily_price_date=view.findViewById<TextView>(R.id.daily_price_date)
    val daily_price_image=view.findViewById<ImageView>(R.id.daily_price_image)
}