package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.DailyPriceModel
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter

class DailyPriceAdapter (val context: Context,val dailyPriceList:List<DailyPriceModel>) : RecyclerView.Adapter<DailyPriceHolder>(){

   // val sec=SectionedRecyclerViewAdapter

    val viewpool=RecyclerView.RecycledViewPool()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyPriceHolder {
        return DailyPriceHolder(LayoutInflater.from(parent.context).inflate(R.layout.daily_price_row, parent, false))
    }

    override fun getItemCount(): Int {
        return dailyPriceList.size

    }

    override fun onBindViewHolder(holder: DailyPriceHolder, position: Int) {
        holder.daily_trade_type.text=dailyPriceList[position].crop_type
        val parent=dailyPriceList[position]
        val layout_Manager=LinearLayoutManager(holder.daily_price_item_recycle.context,LinearLayoutManager.VERTICAL,false)
        layout_Manager.initialPrefetchItemCount=4
        holder.daily_price_item_recycle.apply {
            layoutManager=layout_Manager
            adapter=DailyPriceItemAdapter(context,parent.crop_price_model)
            setRecycledViewPool(viewpool)
        }

    }
}

class DailyPriceHolder(view: View): RecyclerView.ViewHolder(view) {
    val daily_trade_type=view.findViewById<TextView>(R.id.dail_price_title)
    val daily_price_item_recycle=view.findViewById<RecyclerView>(R.id.daily_pric_row_rcycle)

}

