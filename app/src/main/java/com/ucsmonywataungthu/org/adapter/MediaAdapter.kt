package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.master.exoplayer.MasterExoPlayer
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.MediaModel
import kotlinx.android.synthetic.main.master_exoplayer_item.view.*
import me.myatminsoe.mdetect.MDetect
import me.myatminsoe.mdetect.MMTextView
import me.myatminsoe.mdetect.Rabbit

class MediaAdapter(val context: Context, val videoList:List<MediaModel>) : RecyclerView.Adapter<Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.master_exoplayer_item, parent, false))
    }

    override fun getItemCount(): Int {
        return videoList.size

    }
    override fun onBindViewHolder(holder: Holder, position: Int) {

        if(MDetect.isUnicode()){
            holder.text.text=videoList[position].media_title
        }else{
            holder.text.text=Rabbit.uni2zg(videoList[position].media_title)
        }

        holder.textDate.text = videoList[position].created_at.toString()
        holder.frame.url=APIInitiate.PIC_URL+videoList[position].video_link
        holder.image.setImageResource(R.drawable.exo_edit_mode_logo)
        holder.frame.imageView=holder.image
        //holder.frame.url="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        holder.ivVolume.setOnClickListener {
            holder.frame.isMute=!holder.frame.isMute
            Toast.makeText(context,APIInitiate.PIC_URL+videoList[position].video_link,Toast.LENGTH_LONG).show()
        }
    }
}

class Holder(view: View): RecyclerView.ViewHolder(view) {

    var image=view.findViewById<AppCompatImageView>(R.id.image)
    var frame=view.findViewById<MasterExoPlayer>(R.id.frame)
    var text=view.findViewById<MMTextView>(R.id.textTitle)
    val textDate = view.findViewById<AppCompatTextView>(R.id.textDate)
    var ivVolume=view.findViewById<AppCompatImageView>(R.id.ivVolume)

}
