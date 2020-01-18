package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.MediaModel
import me.myatminsoe.mdetect.MDetect
import me.myatminsoe.mdetect.MMTextView
import me.myatminsoe.mdetect.Rabbit

class NewMdiaAdapter(val context: Context, val videoList:List<MediaModel>) : RecyclerView.Adapter<MediaHolder>(){

    var TAG = NewMdiaAdapter::class.java.simpleName
    var exoPlayer:SimpleExoPlayer? = null
    /*companion object{

        fun pausePlayer(){
            if (exoPlayer != null) {
                exoPlayer!!.setPlayWhenReady(false);
                exoPlayer!!.stop()
                exoPlayer!!.seekTo(0)
            }

            Log.i("hiii","back start")
         }
    }*/

    fun pausePlayer(){

    }

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




       /* if(!"NewMediaAdapter".equals(TAG)){
            exoPlayer?.release()
        }*/
    }


}

class MediaHolder(view: View): RecyclerView.ViewHolder(view) {
    var video = view.findViewById<com.google.android.exoplayer2.ui.SimpleExoPlayerView>(R.id.exolayer_view)
    val media_title = view.findViewById<MMTextView>(R.id.media_title)
    val media_date = view.findViewById<TextView>(R.id.media_date)
 }
