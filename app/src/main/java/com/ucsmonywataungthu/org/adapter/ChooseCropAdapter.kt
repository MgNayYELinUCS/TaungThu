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
import com.ucsmonywataungthu.org.CropDetailActivity
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.HomeModel

class ChooseCropAdapter (val context: Context, val cropList:List<HomeModel>) : RecyclerView.Adapter<MyHolder1>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder1 {
        return MyHolder1(LayoutInflater.from(parent.context).inflate(R.layout.choose_crop_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cropList.size

    }

    override fun onBindViewHolder(holder: MyHolder1, position: Int) {
        holder.homeImg.setImageResource(cropList[position].img)
        holder.txtCropName.text = cropList[position].name
        holder.detail_view.setOnClickListener{
            context.startActivity(Intent(context, CropDetailActivity::class.java))
        }
    }
}

class MyHolder1(view: View): RecyclerView.ViewHolder(view) {
    val txtCropName=view.findViewById<TextView>(R.id.choose_crop_name)
    val homeImg=view.findViewById<ImageView>(R.id.choose_crop_img)
    val detail_view=view.findViewById<CardView>(R.id.choose_detail_view)

}
