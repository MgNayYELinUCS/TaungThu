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

class MediaFragment() : Fragment() {
    var video_list:ArrayList<String>?=null
    var videoId:String?=null
    var youtube_video:com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View =inflater.inflate(com.ucsmonywataungthu.org.R.layout.activity_media_fragment, container, false)

        val videoList = ArrayList<VideoModel>()
        videoList.add(VideoModel( "ru0K8uYEZWw","ေျမပဲစိုက္နည္း video ေလးတင္ေပးလုိက္ပါတယ္"))
        videoList.add(VideoModel( "ru0K8uYEZWw","ေျမပဲစိုက္နည္း video ေလးတင္ေပးလုိက္ပါတယ္"))
        videoList.add(VideoModel( "ru0K8uYEZWw","ေျမပဲစိုက္နည္း video ေလးတင္ေပးလုိက္ပါတယ္"))
        videoList.add(VideoModel(  "ru0K8uYEZWw","ေျမပဲစိုက္နည္း video ေလးတင္ေပးလုိက္ပါတယ္"))
        videoList.add(VideoModel( "ru0K8uYEZWw","ေျမပဲစိုက္နည္း video ေလးတင္ေပးလုိက္ပါတယ္"))
        videoList.add(VideoModel("ru0K8uYEZWw","ေျမပဲစိုက္နည္း video ေလးတင္ေပးလုိက္ပါတယ္"))


        var mainListRecycler= view.findViewById<RecyclerView>(R.id.video_recycler)
        mainListRecycler.layoutManager = GridLayoutManager(context,1) as RecyclerView.LayoutManager?
        var adapter = VideoAdapter(context!!,videoList)
        mainListRecycler.adapter = adapter


        youtube_video=view.findViewById(R.id.youtube_vv)
        videoId="ZbZSe6N_BXs"
        playvideo(videoId)


        return view

    }
    fun playvideo(videoId: String?) {
        youtube_video!!.initialize(YouTubePlayerInitListener { initializedYouTubePlayer ->
            initializedYouTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    val videoId = videoId
                    initializedYouTubePlayer.loadVideo(this@MediaFragment.videoId.toString(), 0f)
                    initializedYouTubePlayer.pause()
                }
            })
        }, true)
        val uiController = youtube_video!!.getPlayerUIController()

    }


}