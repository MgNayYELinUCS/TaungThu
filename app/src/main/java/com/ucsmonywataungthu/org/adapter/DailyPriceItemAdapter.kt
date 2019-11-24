package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.CropPriceModel

class DailyPriceItemAdapter (val context: Context, val dailyPriceItemList: List<CropPriceModel>) : RecyclerView.Adapter<DailyPriceItemHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyPriceItemHolder {
        return DailyPriceItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.daily_proce_item_row, parent, false))
    }

    override fun getItemCount(): Int {
        return dailyPriceItemList.size

    }

    override fun onBindViewHolder(holder: DailyPriceItemHolder, position: Int) {

            holder.daily_trade_name.text = dailyPriceItemList[position].crop_name
            holder.daily_trade_unit.text = dailyPriceItemList[position].crop_unit
            holder.daily_trade_price.text = dailyPriceItemList[position].crop_price
    }
}

class DailyPriceItemHolder(view: View): RecyclerView.ViewHolder(view) {
    val daily_trade_name=view.findViewById<TextView>(R.id.daily_trade_name)
    val daily_trade_unit=view.findViewById<TextView>(R.id.daily_trade_unit)
    val daily_trade_price=view.findViewById<TextView>(R.id.daily_trade_price)


}

