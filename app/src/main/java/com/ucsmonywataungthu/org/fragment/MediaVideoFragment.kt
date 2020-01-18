package com.ucsmonywataungthu.org.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService

import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.AnimalHusbandryAdapter
import com.ucsmonywataungthu.org.adapter.NewMdiaAdapter
import com.ucsmonywataungthu.org.model.AnimalModel
import com.ucsmonywataungthu.org.model.MediaModel
import retrofit2.Call
import retrofit2.Response

class MediaVideoFragment : Fragment() {
    internal var api: APIService? = null
    var adapter :NewMdiaAdapter?=null
    var apiService: APIService = APIInitiate.client.create((APIService::class.java))
    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
             if(dy>0){
                //NewMdiaAdapter.pausePlayer()
            }else{
                //NewMdiaAdapter.pausePlayer()
            }

        }
    }

    override fun onDetach() {
        super.onDetach()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val view= inflater.inflate(R.layout.fragment_media_video, container, false)

         var mainListRecycler= view.findViewById<RecyclerView>(R.id.media_recycler)
         mainListRecycler.layoutManager = GridLayoutManager(context,1) as RecyclerView.LayoutManager?

        mainListRecycler.addOnScrollListener(onScrollListener)

         val call=apiService.getMediaList()
         call.enqueue(object : retrofit2.Callback<List<MediaModel>> {
             override fun onFailure(call: Call<List<MediaModel>>, t: Throwable) {
                 Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
             }

             override fun onResponse(call: Call<List<MediaModel>>, response: Response<List<MediaModel>>) {
                 //cropList=null
                 val mediaList=response!!.body()
                  adapter = NewMdiaAdapter(context!!, mediaList!!)
                 mainListRecycler.adapter = adapter
             }
         })
         return view
    }
}
