package com.ucsmonywataungthu.org.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.NewsAdapter
import com.ucsmonywataungthu.org.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment(){
    var apiService: APIService = APIInitiate.client.create((APIService::class.java))
    var newsList:List<News>?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View =inflater.inflate(R.layout.activity_news_fragment, container, false)

       var newRecycler =view.findViewById<RecyclerView>(R.id.newsRecycler)
        newRecycler.layoutManager=LinearLayoutManager(context)


        val call=apiService.getNews()
        call.enqueue(object : Callback<List<News>> {
            override fun onFailure(call: Call<List<News>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {

                newsList=response.body()
                newRecycler.adapter= NewsAdapter(context!!,newsList!!)
            }


        })

        return view

    }
}