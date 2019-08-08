package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener
import com.ucsmonywataungthu.org.Activity.VideoFullViewActivity
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.fragment.MediaFragment
import com.ucsmonywataungthu.org.model.VideoModel


class VideoAdapter (val context: Context, val videoList:List<VideoModel>) : RecyclerView.Adapter<MyHolder5>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder5 {
        return MyHolder5(LayoutInflater.from(parent.context).inflate(R.layout.video_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return videoList.size

    }

    override fun onBindViewHolder(holder: MyHolder5, position: Int) {
        holder.video!!.initialize(YouTubePlayerInitListener { initializedYouTubePlayer ->
            initializedYouTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    initializedYouTubePlayer.loadVideo(videoList.get(position).video_uri.toString(),0f)
                    initializedYouTubePlayer.pause()
                }
            })
        }, true)
        val uiController =holder.video.getPlayerUIController()

        holder.videwo_des.text=videoList.get(position).video_des

        holder.video_layout.setOnClickListener{
            val intent=Intent(context,VideoFullViewActivity::class.java)
            intent.putExtra("videolink",videoList.get(position).video_uri)
            context.startActivity(intent)

        }


    }
}

class MyHolder5(view: View): RecyclerView.ViewHolder(view) {
   val video=view.findViewById<com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView>(R.id.video_view)
    val videwo_des=view.findViewById<TextView>(R.id.video_description)
    val video_layout=view.findViewById<LinearLayout>(R.id.video_layout)
}
