package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.KnowledgeAdapter
import com.ucsmonywataungthu.org.model.Knowledge
import kotlinx.android.synthetic.main.activity_knowledge.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KnowledgeActivity : AppCompatActivity() {
    lateinit var apiService: APIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledge)
        setTitle(null)
        setSupportActionBar(knowledge_toolbar)
        knowledge_toolbar.setNavigationOnClickListener { this.onBackPressed() }
        apiService= APIInitiate.client.create((APIService::class.java))

        knowledge_recycler.layoutManager=LinearLayoutManager(this)
        apiService.getKnowledge().enqueue(object :Callback<Knowledge>{
            override fun onFailure(call: Call<Knowledge>, t: Throwable) {

            }

            override fun onResponse(call: Call<Knowledge>, response: Response<Knowledge>) {
                val kList= response.body()!!.knowledge_get_all
                if (kList.isEmpty()){

                }else{
                    knowledge_recycler.adapter=KnowledgeAdapter(this@KnowledgeActivity,kList)
                }
            }
        })
    }
}
