package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.video.VideoListener
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.MediaModel
import me.myatminsoe.mdetect.MDetect
import me.myatminsoe.mdetect.MMTextView
import me.myatminsoe.mdetect.Rabbit

class NewMdiaAdapter(val context: Context, val videoList:List<MediaModel>) : RecyclerView.Adapter<MediaHolder>(){

    var TAG = NewMdiaAdapter::class.java.simpleName
    var exoPlayer:SimpleExoPlayer? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaHolder {
        MDetect.init(context)
        return MediaHolder(LayoutInflater.from(parent.context).inflate(R.layout.media_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return videoList.size

    }
    override fun onBindViewHolder(holder: MediaHolder, position: Int) {

        if(MDetect.isUnicode()){
            holder.media_title.text =  videoList[position].media_title.toString()
        }else{
            holder.media_title.text = Rabbit.uni2zg(videoList[position].media_title.toString())
        }

        holder.media_date.text = videoList[position].created_at.toString()+""
            //videoList[position].created_at.toString()+""
        val trackSelector = DefaultTrackSelector()


        exoPlayer = ExoPlayerFactory.newSimpleInstance(context,trackSelector)
        holder.video?.player = exoPlayer
        val mediaUri = Uri.parse(APIInitiate.PIC_URL+videoList[position].video_link)
        val userAgent = Util.getUserAgent(context, "ExoPlayer")
        val mediaSource = ExtractorMediaSource(mediaUri,
            DefaultDataSourceFactory(context, userAgent),
            DefaultExtractorsFactory(), null, null)
        exoPlayer?.setPlayWhenReady(false)
        exoPlayer?.prepare(mediaSource)


        holder.back.setOnClickListener{
            Toast.makeText(context,"hi",Toast.LENGTH_LONG).show()
            exoPlayer!!.setPlayWhenReady(false)
            exoPlayer!!.stop()
            exoPlayer!!.seekTo(0)
        }
    }
}

class MediaHolder(view: View): RecyclerView.ViewHolder(view) {
    var video = view.findViewById<com.google.android.exoplayer2.ui.SimpleExoPlayerView>(R.id.exolayer_view)
    val media_title = view.findViewById<MMTextView>(R.id.media_title)
    val media_date = view.findViewById<TextView>(R.id.media_date)
    val back = view.findViewById<ImageView>(R.id.back)
 }
