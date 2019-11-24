package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Activity.MerchantProfileActivity
import com.ucsmonywataungthu.org.Interface.CropPriceClickListener
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.CropPriceModel
import kotlinx.android.synthetic.main.merchant_price_row.view.*

class MerchantDetailPriceAdapter (val context: Context, val cropPriceList: List<CropPriceModel>?) : RecyclerView.Adapter<MerchantDetailPrice>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MerchantDetailPrice {
        return MerchantDetailPrice(LayoutInflater.from(parent.context).inflate(R.layout.merchant_price_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cropPriceList!!.size

    }

    override fun onBindViewHolder(holder: MerchantDetailPrice, position: Int) {
        holder.txt_merchant_crop_name.text=cropPriceList!!.get(position).crop_name
        holder.txt_merchant_crop_unit.text=cropPriceList!!.get(position).crop_unit
        holder.txt_merchant_crop_price.text=cropPriceList!!.get(position).crop_price



    }
}

class MerchantDetailPrice(view: View): RecyclerView.ViewHolder(view) {
    val txt_merchant_crop_name=view.findViewById<TextView>(R.id.txt_merchant_crop_name)
    val txt_merchant_crop_unit=view.findViewById<TextView>(R.id.txt_merchant_crop_unit)
    val txt_merchant_crop_price=view.findViewById<TextView>(R.id.txt_merchant_crop_price)

}