package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.MerchantModel
import com.ucsmonywataungthu.org.model.MerchantPriceModel

class MerchantPriceAdapter (val context: Context, val merchantPriceList:List<MerchantPriceModel>) : RecyclerView.Adapter<MyHolder9>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder9 {
        return MyHolder9(LayoutInflater.from(parent.context).inflate(R.layout.merchant_price_row, parent, false))
    }

    override fun getItemCount(): Int {
        return merchantPriceList.size

    }

    override fun onBindViewHolder(holder: MyHolder9, position: Int) {
        holder.txt_merchant_name.text = merchantPriceList[position].crop_name
        holder.txt_merchant_unit.text = merchantPriceList[position].crop_unit
        holder.txt_merchant_price.text = merchantPriceList[position].crop_price

    }
}

class MyHolder9(view: View): RecyclerView.ViewHolder(view) {
    val txt_merchant_name=view.findViewById<TextView>(R.id.merchant_price_name)
    val txt_merchant_unit=view.findViewById<TextView>(R.id.merchant_price_unit)
    val txt_merchant_price=view.findViewById<TextView>(R.id.merchant_price_price)

    val merchant_cardview=view.findViewById<CardView>(R.id.merchant_cardview)

}
