package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.ChooseCropAdapter
import com.ucsmonywataungthu.org.model.CropDetailsGetAll
import com.ucsmonywataungthu.org.model.CropSubcategory
import com.ucsmonywataungthu.org.model.HomeModel
import com.ucsmonywataungthu.org.model.ServerResult
import kotlinx.android.synthetic.main.activity_choose_crop.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ChooseCropActivity : AppCompatActivity() {
    internal var api: APIService? = null
    var cropList:List<CropSubcategory>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_crop)

        val categoryList = ArrayList<HomeModel>()

        var mainListRecycler= findViewById<RecyclerView>(R.id.choose_crop_recycle)
        mainListRecycler.layoutManager = GridLayoutManager(this,3) as RecyclerView.LayoutManager?

        var apiService: APIService = APIInitiate.client.create((APIService::class.java))
        val call=apiService.getCropList()
        call.enqueue(object : retrofit2.Callback<ServerResult> {
            override fun onFailure(call: Call<ServerResult>, t: Throwable) {
                    Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ServerResult>, response: Response<ServerResult>) {
                cropList=response.body()!!.result
                var adapter = ChooseCropAdapter(applicationContext, cropList!!)
                mainListRecycler.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })
    }

}
