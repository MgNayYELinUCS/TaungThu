package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Activity.AnimalHusbandry
import com.ucsmonywataungthu.org.Activity.ChooseCropActivity
import com.ucsmonywataungthu.org.Activity.LoginActivity
import com.ucsmonywataungthu.org.Activity.TradeCenter
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.HomeModel

class HomeAdapter (val context: Context,val cropList:List<HomeModel>) : RecyclerView.Adapter<MyHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_list_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cropList.size

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.homeImg.setImageResource(cropList[position].img)
        holder.txtCropName.text = cropList[position].name
        holder.homeImg.setOnClickListener{
            when(position) {
                0 -> context.startActivity(Intent(context, ChooseCropActivity::class.java))
                1-> context.startActivity(Intent(context, AnimalHusbandry::class.java))
                2 -> context.startActivity(Intent(context, TradeCenter::class.java))
                else ->  context.startActivity(Intent(context, LoginActivity::class.java))
            }

        }
    }
}

class MyHolder(view: View): RecyclerView.ViewHolder(view) {
    val txtCropName=view.findViewById<TextView>(R.id.cropname)
    val homeImg=view.findViewById<ImageView>(R.id.home_img)
    //val detail_view=view.findViewById<CardView>(R.id.detail_view)
}

