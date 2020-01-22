package com.ucsmonywataungthu.org.fragment

import android.app.Activity
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ui.PlayerView
import com.master.exoplayer.*
import com.ucsmonywataungthu.org.Activity.MainActivity
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.MediaAdapter
import com.ucsmonywataungthu.org.model.MediaModel
import retrofit2.Call
import retrofit2.Response

class MediaVideoFragment : Fragment() {

    internal var api: APIService? = null
    var adapter :MediaAdapter?=null
    lateinit var masterExoPlayerHelper: MasterExoPlayerHelper

    var apiService: APIService = APIInitiate.client.create((APIService::class.java))
     var mainListRecycler:RecyclerView?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_media_video, container, false)

          mainListRecycler= view.findViewById<RecyclerView>(R.id.media_recycler)
         mainListRecycler!!.layoutManager = GridLayoutManager(context,1)

        masterExoPlayerHelper = MasterExoPlayerHelper(mContext = context!!,id = R.id.frame)
        //setAdapter()
        masterExoPlayerHelper.makeLifeCycleAware(context as AppCompatActivity)


         val call=apiService.getMediaList()
         call.enqueue(object : retrofit2.Callback<List<MediaModel>> {
             override fun onFailure(call: Call<List<MediaModel>>, t: Throwable) {
                 Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
             }

             override fun onResponse(call: Call<List<MediaModel>>, response: Response<List<MediaModel>>) {
                 //cropList=null
                 val mediaList=response!!.body()
                 if (!mediaList!!.isEmpty()){

                     adapter = MediaAdapter(context!!, mediaList!!)

                     mainListRecycler!!.adapter=adapter

                 }

             }
         })


        //adapter!!.notifyDataSetChanged()
        masterExoPlayerHelper.attachToRecyclerView(mainListRecycler!!)


         return view
    }

    override fun onDetach() {
        super.onDetach()

        masterExoPlayerHelper.exoPlayerHelper.stop()
    }

}
