package com.ucsmonywataungthu.org.adapter

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.InfoModel
import android.content.ActivityNotFoundException

class ContactUsAdapter (val context: Context, val infoList:List<InfoModel>) : RecyclerView.Adapter<MyHolder7>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder7 {
        return MyHolder7(LayoutInflater.from(parent.context).inflate(R.layout.info_layout_row, parent, false))
    }

    override fun getItemCount(): Int {
        return infoList.size

    }

    override fun onBindViewHolder(holder: MyHolder7, position: Int) {
        holder.info_image.setImageResource(infoList.get(position).info_image)
        holder.info_title.text=infoList.get(position).info_title



        holder.info_layout.setOnClickListener {


            if (infoList.get(position).info_title.equals("web site ကိုလာေရာက္ေလ့လာရန္")) {
                val url: String = "https://www.google.com"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(url))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)

            } else if (infoList.get(position).info_title.equals("facebook စာမ်က္ႏွာသြားရန္")) {
                val url: String = "https://www.facebook.com"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(url))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            } else if (infoList.get(position).info_title.equals("09691626426")) {

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:09691626426")
                callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(callIntent)
                Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()


            } else if (infoList.get(position).info_title.equals("monywacu.taungthu@gmail.com")) {
                val mailto = "mailto:monywacu.taungthu@gmail.com" +
                        "?cc=" + "" +
                        "&subject=" + Uri.encode("") +
                        "&body=" + Uri.encode("")

                val emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.data = Uri.parse(mailto)

                try {
                    emailIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                    context.startActivity(emailIntent)
                } catch (e: ActivityNotFoundException) {
                    //TODO: Handle case where no email app is available
                }


            }


        }

    }
}

class MyHolder7(view: View): RecyclerView.ViewHolder(view) {
    val info_image=view.findViewById<ImageView>(R.id.info_image)
    val info_title=view.findViewById<TextView>(R.id.info_title)
    val info_layout=view.findViewById<ConstraintLayout>(R.id.infoLayout)
}
