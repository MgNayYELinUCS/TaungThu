package com.ucsmonywataungthu.org.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.*
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import androidx.recyclerview.widget.GridLayoutManager
import com.ucsmonywataungthu.org.adapter.VideoAdapter
import com.ucsmonywataungthu.org.model.VideoModel
import com.ucsmonywataungthu.org.Interface.VideoPositionClick


class MediaFragment() : Fragment(), VideoPositionClick {
    var youtube_video:com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView? = null
    val videoList = ArrayList<VideoModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View =inflater.inflate(com.ucsmonywataungthu.org.R.layout.activity_media_fragment, container, false)

        videoList.add(VideoModel( "ru0K8uYEZWw","ေျမပဲစိုက္နည္း video ေလးတင္ေပးလုိက္ပါတယ္"))
        videoList.add(VideoModel( "ru0K8uYEZWw","ေျမပဲစိုက္နည္း video ေလးတင္ေပးလုိက္ပါတယ္"))
        videoList.add(VideoModel( "ru0K8uYEZWw","ေျမပဲစိုက္နည္း video ေလးတင္ေပးလုိက္ပါတယ္"))
        videoList.add(VideoModel(  "ZbZSe6N_BXs","ဆန္ video ေလးတင္ေပးလုိက္ပါတယ္"))
        videoList.add(VideoModel( "ZbZSe6N_BXs","ဆန္ video ေလးတင္ေပးလုိက္ပါတယ္"))
        videoList.add(VideoModel("ZbZSe6N_BXs","ဆန္ video ေလးတင္ေပးလုိက္ပါတယ္"))


        var mainListRecycler= view.findViewById<RecyclerView>(R.id.video_recycler)
        mainListRecycler.layoutManager = GridLayoutManager(context,1)
        var adapter = VideoAdapter(context!!,videoList)
        mainListRecycler.adapter = adapter
        adapter.setOnItemClickListener(this)


        youtube_video=view.findViewById(com.ucsmonywataungthu.org.R.id.youtube_vv)
        val videoId="ZbZSe6N_BXs"
        playvideo(videoId,false)



        return view

    }
    fun playvideo(vId: String,play:Boolean) {
        youtube_video!!.initialize(YouTubePlayerInitListener { initializedYouTubePlayer ->
            initializedYouTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    initializedYouTubePlayer.loadVideo(vId, 0f)
                    if (play){
                        initializedYouTubePlayer.play()
                    }else{
                        initializedYouTubePlayer.pause()
                    }
                    //initializedYouTubePlayer.play()
                }
            })
        }, true)
    }
    override fun onVideoPositionClickListener(position: Int) {
        Toast.makeText(context,videoList.get(position).video_uri, Toast.LENGTH_LONG).show()
        playvideo(videoList.get(position).video_uri,true)

    }
}