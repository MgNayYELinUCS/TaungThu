package com.ucsmonywataungthu.org.Merchant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ucsmonywataungthu.org.R
import kotlinx.android.synthetic.main.activity_merchant_detail.*
import kotlinx.android.synthetic.main.content_scrolling_merchant_detail.*
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Interface.CropPriceClickListener
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.adapter.MerchantDetailPriceAdapter
import com.ucsmonywataungthu.org.adapter.MerchantPriceAdapter
import com.ucsmonywataungthu.org.model.CropPriceModel
import kotlinx.android.synthetic.main.activity_merchant_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MerchantDetailActivity : AppCompatActivity(){

    lateinit var apiService: APIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_detail)
        apiService= APIInitiate.client.create((APIService::class.java))

        val categoryList = ArrayList<CropPriceModel>()
        categoryList.add(CropPriceModel(1,"HII","HII","1000"))

        merchant_price_recycle.layoutManager = GridLayoutManager(applicationContext,1) as RecyclerView.LayoutManager?
      /*  var adapter = MerchantPriceAdapter(applicationContext,categoryList)
        merchant_price_recycle.adapter = adapter*/

        val intent=intent
        setTitle(intent.getSerializableExtra("mname").toString())

        val merchantId=intent.getSerializableExtra("mid") as Int
        txt_merchant_detail_name.text=intent.getSerializableExtra("mname").toString()
        txt_merchant_main_type.text=intent.getSerializableExtra("mtype").toString()
        txt_merchant_main_address.text=intent.getSerializableExtra("maddress").toString()
        txt_merchant_main_phone.text=intent.getSerializableExtra("mphone").toString()


        apiService.getMerchantPrice(merchantId).enqueue(object: Callback<List<CropPriceModel>> {
            override fun onFailure(call: Call<List<CropPriceModel>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<CropPriceModel>>, response: Response<List<CropPriceModel>>) {
                if (response.isSuccessful){
                    if (!response.body()!!.isEmpty()){
                        val cropPriceList=response.body()
                        var adapter = MerchantDetailPriceAdapter(applicationContext,cropPriceList)
                        merchant_price_recycle.adapter = adapter

                    }

                }

            }


        })

        val phone_no=intent.getSerializableExtra("mphone").toString()
        fab_message.setOnClickListener{
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.putExtra("address",phone_no)
            sendIntent.putExtra("sms_body", "default content")
            sendIntent.type = "vnd.android-dir/mms-sms"
            startActivity(sendIntent)

        }

        fab_contact.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:"+phone_no)
            startActivity(intent)
        }


    }
}
