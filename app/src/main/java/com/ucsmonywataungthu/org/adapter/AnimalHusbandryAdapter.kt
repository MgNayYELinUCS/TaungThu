package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ucsmonywataungthu.org.Activity.AnimalDetailActivity
import com.ucsmonywataungthu.org.Activity.CropDetailActivity
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.AnimalModel
import kotlinx.android.synthetic.main.activity_crop_detail.*

class AnimalHusbandryAdapter (val context: Context, val animalList:List<AnimalModel>) : RecyclerView.Adapter<MyHolder2>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder2 {
        return MyHolder2(LayoutInflater.from(parent.context).inflate(R.layout.animal_detail_row, parent, false))
    }

    override fun getItemCount(): Int {
        return animalList.size

    }

    override fun onBindViewHolder(holder: MyHolder2, position: Int) {
        Glide.with(context).load(APIInitiate.PIC_URL+animalList[position].animal_picture)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.animal_picture)
        holder.animl_name.text = animalList[position].animal_name
        holder.animal_picture.setOnClickListener{
            val intent=Intent(context,AnimalDetailActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("animalpicture",animalList[position].animal_picture)
            intent.putExtra("animalname",animalList[position].animal_name)
            intent.putExtra("animaldesc",animalList[position].animal_description)
            context.startActivity(intent)
        }
    }
}

class MyHolder2(view: View): RecyclerView.ViewHolder(view) {
    val animal_picture=view.findViewById<ImageView>(R.id.choose_animal_img)
    val animl_name=view.findViewById<TextView>(R.id.choose_animal_name)
    //val detail_view=view.findViewById<CardView>(R.id.choose_detail_view)

}
