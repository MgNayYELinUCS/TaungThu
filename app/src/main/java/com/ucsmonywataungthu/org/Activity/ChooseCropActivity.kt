package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.ChooseCropAdapter
import kotlinx.android.synthetic.main.activity_choose_crop.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import com.google.android.material.chip.Chip
import com.ucsmonywataungthu.org.model.*


class ChooseCropActivity : AppCompatActivity() {
    internal var api: APIService? = null
    var apiService: APIService = APIInitiate.client.create((APIService::class.java))
    var cropList:List<CropSubcategory>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_crop)
        setSupportActionBar(toolbar_Crop)
        toolbar_Crop.setNavigationOnClickListener { this.onBackPressed() }

        var mainListRecycler= findViewById<RecyclerView>(R.id.choose_crop_recycle)
        mainListRecycler.layoutManager = GridLayoutManager(this,3)

        getAllCrop()



        chipGroup.setOnCheckedChangeListener(ChipGroup.OnCheckedChangeListener { chipGroup, i ->
            val chip = chipGroup.findViewById<Chip>(i)


            if (chip != null)
                when(i){
                    1->getAllCrop()
                    2->filterCropSubCategory(1)
                    3->filterCropSubCategory(2)
                    4->filterCropSubCategory(3)
                    5->filterCropSubCategory(4)
                }

                Toast.makeText(applicationContext, "Chip is "+i+"" + chip.text.toString(), Toast.LENGTH_SHORT).show()

        })
    }

    public fun filterCropSubCategory(i: Int) {
        Log.i("id ",i.toString())
        val call=apiService.getCropCategory(i)
        call.enqueue(object : retrofit2.Callback<CropResult> {
            override fun onFailure(call: Call<CropResult>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<CropResult>, response: Response<CropResult>) {
                //cropList=null
                cropList=response.body()!!.cropresult
                var adapter = ChooseCropAdapter(applicationContext, cropList!!)
                choose_crop_recycle.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })

    }
    public fun getAllCrop(){
        val call=apiService.getCropSubCategory()
        call.enqueue(object : retrofit2.Callback<ServerResult> {
            override fun onFailure(call: Call<ServerResult>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ServerResult>, response: Response<ServerResult>) {
                //cropList=null
                cropList=response.body()!!.result
                var adapter = ChooseCropAdapter(applicationContext, cropList!!)
                choose_crop_recycle.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })
    }

}
