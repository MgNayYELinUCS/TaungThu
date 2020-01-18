package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Merchant.MerchantDetailActivity
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.MerchantModel
import me.myatminsoe.mdetect.MDetect
import me.myatminsoe.mdetect.Rabbit


class MerchantAdapter (val context: Context, val merchantList: List<MerchantModel>) : RecyclerView.Adapter<MyHolder3>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder3 {
        return MyHolder3(LayoutInflater.from(parent.context).inflate(R.layout.merchant_category_row, parent, false))
    }

    override fun getItemCount(): Int {
        return merchantList.size

    }

    override fun onBindViewHolder(holder: MyHolder3, position: Int) {

        holder.merchant_image.setImageResource(R.mipmap.home)
        if(MDetect.isUnicode()){
            holder.txt_merchant_name.text = merchantList[position].merchant_name
            holder.txt_merchant_type.text = merchantList[position].merchant_type.merchant_type_name

        }else{
            holder.txt_merchant_name.text = Rabbit.uni2zg(merchantList[position].merchant_name)
            holder.txt_merchant_type.text = Rabbit.uni2zg(merchantList[position].merchant_type.merchant_type_name)

        }

        holder.merchant_cardview.setOnClickListener{
            val intent=Intent(context, MerchantDetailActivity::class.java)
            intent.putExtra("mid",merchantList[position].id)
            intent.putExtra("mname",merchantList[position].merchant_name)
            intent.putExtra("maddress",merchantList[position].merchant_address)
            intent.putExtra("mtype",merchantList[position].merchant_type!!.merchant_type_name)
            intent.putExtra("mphone",merchantList[position].phone_number)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}

class MyHolder3(view: View): RecyclerView.ViewHolder(view) {
    val txt_merchant_name=view.findViewById<TextView>(R.id.txt_merchant_name)
    val txt_merchant_type=view.findViewById<TextView>(R.id.txt_merchant_type)
    val merchant_image=view.findViewById<ImageView>(R.id.merchant_image)
    val merchant_cardview=view.findViewById<CardView>(R.id.merchant_cardview)

}
