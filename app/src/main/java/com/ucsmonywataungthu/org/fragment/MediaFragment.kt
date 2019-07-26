package com.ucsmonywataungthu.org.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.net.Uri
import com.ucsmonywataungthu.org.R
import kotlinx.android.synthetic.main.activity_media_fragment.*
import android.media.MediaPlayer
import android.widget.*
import kotlinx.android.synthetic.main.activity_media_fragment.view.*


class MediaFragment : Fragment(), AdapterView.OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(position) {
            0 ->Toast.makeText(context,"q",Toast.LENGTH_SHORT).show()
            1-> Toast.makeText(context,"qd",Toast.LENGTH_SHORT).show()
            2 ->Toast.makeText(context,"q1",Toast.LENGTH_SHORT).show()
            else -> println("Number too high")
        }
    }

    var video_list:ArrayList<String>?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View =inflater.inflate(com.ucsmonywataungthu.org.R.layout.activity_media_fragment, container, false)

        video_list=ArrayList<String>()
        video_list!!.add("hi")
        video_list!!.add("hi")
        video_list!!.add("hi")

        var adapter1=ArrayAdapter(context,android.R.layout.simple_list_item_1,video_list)

        view!!.video_listview.adapter=adapter1
        view!!.video_listview.setOnItemClickListener(this)


        val video = view.findViewById(com.ucsmonywataungthu.org.R.id.video) as VideoView
        video.setOnPreparedListener {
            video.requestFocus()
            video.start()
        }
        video.setVideoURI(Uri.parse("android.resource://" + getActivity()!!.getPackageName() + "/" + com.ucsmonywataungthu.org.R.raw.g))
        video.setMediaController(MediaController(context))
        video.requestFocus()
        return view

    }
    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        video_list=ArrayList<String>()
        video_list!!.add("hi")
        video_list!!.add("hi")
        video_list!!.add("hi")

        var adapter1=ArrayAdapter(context,android.R.layout.simple_list_item_1,video_list)

        view!!.video_listview.adapter=adapter1
        view!!.video_listview.setOnItemClickListener(this)
    }*/

}