package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ucsmonywataungthu.org.Activity.CropDetailActivity
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.CropSubcategory

class ChooseCropAdapter (val context: Context, val cropList:List<CropSubcategory>) : RecyclerView.Adapter<MyHolder1>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder1 {
        return MyHolder1(LayoutInflater.from(parent.context).inflate(R.layout.choose_crop_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cropList.size

    }

    override fun onBindViewHolder(holder: MyHolder1, position: Int) {

        Glide.with(context).load(APIInitiate.PIC_URL+cropList[position].crop_subcategory_image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.homeImg)
        holder.txtCropName.text = cropList[position].crop_subcategory_name
        holder.detail_view.setOnClickListener{
            //Toast.makeText(context,APIInitiate.PIC_URL+cropList[position].crop_subcategory_image,Toast.LENGTH_SHORT).show()
            val intent = Intent(context, CropDetailActivity::class.java)
            intent.putExtra("cropdetailid",cropList[position].id)
            intent.putExtra("img",cropList[position].crop_subcategory_image)
           // Toast.makeText(context,cropList[position].id.toString(),Toast.LENGTH_SHORT).show()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
            }
    }
}

class MyHolder1(view: View): RecyclerView.ViewHolder(view) {
    val txtCropName=view.findViewById<TextView>(R.id.choose_crop_name)
    val homeImg=view.findViewById<ImageView>(R.id.choose_crop_img)
    val detail_view=view.findViewById<LinearLayout>(R.id.cropdetail)

}
