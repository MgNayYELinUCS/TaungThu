package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener
import com.ucsmonywataungthu.org.R
import kotlinx.android.synthetic.main.activity_video_full_view.*

class VideoFullViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_full_view)

        val intent=intent
        val video_uri=intent.getSerializableExtra("videolink")

        video_fullview!!.initialize(YouTubePlayerInitListener { initializedYouTubePlayer ->
                initializedYouTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady() {
                        initializedYouTubePlayer.loadVideo(video_uri.toString(),0f)
                        initializedYouTubePlayer.play()
                    }
                })
            }, true)
            val uiController =video_fullview.getPlayerUIController()

    }
}
