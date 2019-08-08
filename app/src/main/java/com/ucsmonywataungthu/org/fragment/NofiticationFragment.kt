package com.ucsmonywataungthu.org.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.HomeAdapter
import com.ucsmonywataungthu.org.adapter.NewsAdapter
import com.ucsmonywataungthu.org.adapter.NotificationAdapter
import com.ucsmonywataungthu.org.model.*
import retrofit2.Call
import retrofit2.Response

class NofiticationFragment : Fragment(){

    var apiService: APIService = APIInitiate.client.create((APIService::class.java))
    var notificationModelList:List<NotificationModel>?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View =inflater.inflate(R.layout.activity_nofitication_fragment, container, false)


        val callMerchant=apiService.getNotification()
        callMerchant.enqueue(object :retrofit2.Callback<List<NotificationModel>>{
            override fun onFailure(call: Call<List<NotificationModel>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<NotificationModel>>, response: Response<List<NotificationModel>>) {
                notificationModelList=null
                notificationModelList=response.body()
                var mainListRecycler= view.findViewById<RecyclerView>(R.id.news_recycle)
                mainListRecycler.layoutManager = GridLayoutManager(context,1) as RecyclerView.LayoutManager?
                var adapter = NotificationAdapter(context!!,notificationModelList!!)
                mainListRecycler.adapter = adapter
            }
        })
        return view

    }
}