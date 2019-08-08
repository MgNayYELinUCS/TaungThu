package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.MerchantModel



class MerchantSaleandBuyAdapter (val context: Context, val merchantList:List<MerchantModel>) : RecyclerView.Adapter<MyHolder10>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder10{
        return MyHolder10(LayoutInflater.from(parent.context).inflate(R.layout.merchant_buyandsale_row, parent, false))
    }

    override fun getItemCount(): Int {
        return merchantList.size

    }

    override fun onBindViewHolder(holder: MyHolder10, position: Int) {
        holder.txt_merchant_name.text = merchantList[position].merchant_name
        holder.txt_merchant_unit.text = merchantList[position].merchant_type.merchant_type_name
        holder.txt_merchant_price.text = merchantList[position].merchant_type.merchant_type_name

        holder.checkbox_status.setOnCheckedChangeListener{buttonView, isChecked ->
            if(holder.checkbox_status.isChecked){
               holder.setIsRecyclable(false);
                holder.ed_merchant_qty.visibility=View.VISIBLE
                holder.txt_merchant_total.visibility=View.VISIBLE
            }else if(!isChecked){
                holder.setIsRecyclable(true)
                holder.ed_merchant_qty.visibility=View.INVISIBLE
                holder.txt_merchant_total.visibility=View.INVISIBLE
            }
        }
    }
}

class MyHolder10(view: View): RecyclerView.ViewHolder(view) {

    val checkbox_status=view.findViewById<CheckBox>(R.id.checkbox_buyandsale)
    val txt_merchant_name=view.findViewById<TextView>(R.id.merchant_price_saleandbuy_name)
    val txt_merchant_unit=view.findViewById<TextView>(R.id.merchant_price_saleandbuy_unit)
    val txt_merchant_price=view.findViewById<TextView>(R.id.merchant_price_saleandbuy_price)
    val ed_merchant_qty=view.findViewById<EditText>(R.id.merchant_ed_quality)
    val txt_merchant_total=view.findViewById<TextView>(R.id.merchant_price_total)

    val merchant_cardview=view.findViewById<CardView>(R.id.merchant_cardview_saleandbuy)

}
