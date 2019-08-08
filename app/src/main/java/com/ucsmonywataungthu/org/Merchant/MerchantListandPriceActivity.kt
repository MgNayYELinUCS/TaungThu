package com.ucsmonywataungthu.org.Merchant

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.MerchantPriceAdapter
import com.ucsmonywataungthu.org.model.MerchantModel
import com.ucsmonywataungthu.org.model.MerchantPriceModel
import com.ucsmonywataungthu.org.model.MerchantTypeModel
import kotlinx.android.synthetic.main.activity_merchant_listand_price.*
import retrofit2.Call
import retrofit2.Response

class MerchantListandPriceActivity : AppCompatActivity() {

    var apiService: APIService = APIInitiate.client.create((APIService::class.java))
    var merchant_price_list:List<MerchantPriceModel>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_listand_price)

        val intent=intent
        setTitle(intent.getSerializableExtra("mname").toString())
        txt_merchant_main_name.text=intent.getSerializableExtra("mname").toString()
        txt_merchant_main_type.text=intent.getSerializableExtra("mtype").toString()
        txt_merchant_main_address.text=intent.getSerializableExtra("maddress").toString()
        txt_merchant_main_phone.text=intent.getSerializableExtra("mphone").toString()


        val callMerchant=apiService.getMerchantPrice(1)
        callMerchant.enqueue(object :retrofit2.Callback<List<MerchantPriceModel>>{
            override fun onFailure(call: Call<List<MerchantPriceModel>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<MerchantPriceModel>>, response: Response<List<MerchantPriceModel>>) {
                merchant_price_list=null
                merchant_price_list=response.body()
                var mainListRecycler= findViewById<RecyclerView>(R.id.merchant_price_recycle)
                mainListRecycler.layoutManager = GridLayoutManager(applicationContext,1) as RecyclerView.LayoutManager?
                var adapter = MerchantPriceAdapter(applicationContext!!,merchant_price_list!!)
                mainListRecycler.adapter = adapter
            }

        })

        /* val merchantPriceList = ArrayList<MerchantModel>()
         merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး", MerchantTypeModel(1,""),"","",""))
         merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး", MerchantTypeModel(1,""),"","",""))
         merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး", MerchantTypeModel(1,""),"","",""))
         merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး", MerchantTypeModel(1,""),"","",""))
         merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး", MerchantTypeModel(1,""),"","",""))

 */


        btn_sale.setOnClickListener {
            fun showDialog(){
                val array= arrayOf("H","h")
                val builder= AlertDialog.Builder(this)
                builder.setTitle("h")
                builder.setItems(array){dialog, which ->

                }
            }
        }

    }
}
