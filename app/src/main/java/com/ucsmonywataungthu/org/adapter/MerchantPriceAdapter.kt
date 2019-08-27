package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.CropPriceModel
import com.ucsmonywataungthu.org.model.HomeModel

class MerchantPriceAdapter (val context: Context, val cropList:List<CropPriceModel>) : RecyclerView.Adapter<MerchantPrice>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MerchantPrice {
        return MerchantPrice(LayoutInflater.from(parent.context).inflate(R.layout.merchant_price_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cropList.size

    }

    override fun onBindViewHolder(holder: MerchantPrice, position: Int) {
        holder.txt_merchant_crop_name.text=cropList.get(position).crop_name
        holder.txt_merchant_crop_unit.text=cropList.get(position).crop_unit
        holder.txt_merchant_crop_price.text=cropList.get(position).crop_price

    }
}

class MerchantPrice(view: View): RecyclerView.ViewHolder(view) {
    val txt_merchant_crop_name=view.findViewById<TextView>(R.id.txt_merchant_crop_name)
    val txt_merchant_crop_unit=view.findViewById<TextView>(R.id.txt_merchant_crop_unit)
    val txt_merchant_crop_price=view.findViewById<TextView>(R.id.txt_merchant_crop_price)

}