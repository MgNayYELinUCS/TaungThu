package com.ucsmonywataungthu.org.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.model.*
import kotlinx.android.synthetic.main.activity_crop_detail.*
import kotlinx.android.synthetic.main.content_scrolling.*
import retrofit2.Call
import retrofit2.Response


class CropDetailActivity : AppCompatActivity(){
    var apiService: APIService = APIInitiate.client.create((APIService::class.java))
    var cropDetailList:List<CropDetailsGetAll>?=null
    var cid:String?=null

    var crop_detail_name:String?=null
    var crop_detail_stroe_method:String?=null
    var crop_detail_fertilizer:String?=null
    var crop_detail_argi:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.ucsmonywataungthu.org.R.layout.activity_crop_detail)
       // setSupportActionBar(toolbar as Toolbar?)

        val intext:Intent=intent
        val cropid:Int= intext.getSerializableExtra("cropdetailid") as Int
        val img:String=intext.getSerializableExtra("img") as String


        Glide.with(applicationContext).load(APIInitiate.PIC_URL+img)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img_crop)
        CropAllDetail(cropid)

}

    public fun CropAllDetail(i: Int) {
        Log.i("id ",i.toString())
        val call=apiService.getCropDetailCategory(i)
        call.enqueue(object : retrofit2.Callback<CropDetailResult> {
            override fun onFailure(call: Call<CropDetailResult>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<CropDetailResult>, response: Response<CropDetailResult>) {
                //cropList=null
                cropDetailList=response.body()!!.cropdetailresult


                val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, cropDetailList)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner_detail!!.adapter=adapter

                spinner_detail.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                     }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        var cropdetail_model=spinner_detail.selectedItem as CropDetailsGetAll
                        cid=cropdetail_model.id.toString()

                        binddata(position)

                    }

                }
            }
        })

    }
    fun binddata(position: Int) {
        crop_detail_name = cropDetailList!!.get(position).crop_name
        crop_detail_argi = cropDetailList!!.get(position).crop_description
        crop_detail_stroe_method = cropDetailList!!.get(position).crop_store_method
        crop_detail_fertilizer = cropDetailList!!.get(position).crop_fertilizer

        cropdetail_name.text=crop_detail_name.toString()
        cropdetail_store.text=crop_detail_stroe_method.toString()
        cropdetail_dees.text=crop_detail_argi.toString()
        cropdetail_fertilizer.text=crop_detail_fertilizer.toString()

        //Toast.makeText(applicationContext,crop_detail_fertilizer+crop_detail_argi,Toast.LENGTH_SHORT).show()
    }

}
