package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Activity.*
import com.ucsmonywataungthu.org.Merchant.MerchantActivity
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.HomeModel
import me.myatminsoe.mdetect.MDetect
import me.myatminsoe.mdetect.MMTextView
import me.myatminsoe.mdetect.Rabbit

class HomeAdapter (val context: Context,val cropList:List<HomeModel>) : RecyclerView.Adapter<MyHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_list_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cropList.size

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.homeImg.setImageResource(cropList[position].img)
        if(MDetect.isUnicode()){
            holder.txtCropName.text = cropList[position].name
        }else{
            holder.txtCropName.text = Rabbit.uni2zg(cropList[position].name)
        }

        holder.homeImg.setOnClickListener{
            when(position) {
                0 -> context.startActivity(Intent(context, ChooseCropActivity::class.java))
                1-> context.startActivity(Intent(context, AnimalHusbandryActivity::class.java))
                2 -> context.startActivity(Intent(context, MerchantActivity::class.java))
                3 -> context.startActivity(Intent(context, KnowledgeActivity::class.java))
                4 -> context.startActivity(Intent(context, QuestionActivity::class.java))
                5 -> context.startActivity(Intent(context, DailyPriceActivityNew::class.java))



                else ->  context.startActivity(Intent(context, LoginActivity::class.java))
            }

        }
    }
}

class MyHolder(view: View): RecyclerView.ViewHolder(view) {
    val txtCropName=view.findViewById<MMTextView>(R.id.cropname)
    val homeImg=view.findViewById<ImageView>(R.id.home_img)
    //val detail_view=view.findViewById<CardView>(R.id.detail_view)
}

