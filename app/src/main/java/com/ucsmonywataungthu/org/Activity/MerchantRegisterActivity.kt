package com.ucsmonywataungthu.org.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.MerchantType
import com.ucsmonywataungthu.org.model.RegionModel
import com.ucsmonywataungthu.org.model.SuccessUpload
import com.ucsmonywataungthu.org.model.TownshipModel
import kotlinx.android.synthetic.main.activity_merchant_list.*
import kotlinx.android.synthetic.main.activity_merchant_profile.*
import kotlinx.android.synthetic.main.activity_merchant_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MerchantRegisterActivity : AppCompatActivity() {

    lateinit var sharePreferences: SharedPreferences
    lateinit var apiService: APIService
    var merchantRegion:List<RegionModel>?=null
    var merchant_Township:List<TownshipModel>?=null
    var merchantTypeList:List<MerchantType>?=null
    lateinit var name:String
    var type_id:Int = 0
    lateinit var address:String
    lateinit var phone:String
    var city_id:Int = 1
    var lat:Int = 0
    var long:Int = 0
    var user_id=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_register)
        sharePreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        apiService= APIInitiate.client.create((APIService::class.java))
        user_id=sharePreferences.getInt("user_id",0)
        getAllMerchantType()
        getAllCity()



        merchant_register.setOnClickListener {
            name=txt_merchant_register_name.text.toString()


            type_id=merchantTypeList!!.get(spinner_merchant_register_type.selectedItemPosition).id
            //type_id=spinner_merchant_register_type.selectedItemPosition+1
            address=txt_merchant_register_address.text.toString()
            city_id=merchant_Township!!.get(spinner_merchant_register_city.selectedItemPosition).id
            // city_id=spinner_merchant_register_city.selectedItemPosition+1
            phone=txt_merchant_register_phoneno.text.toString()
            apiService.merchantRegister(name,type_id,address,phone,city_id,lat,long,user_id).enqueue(object :Callback<SuccessUpload>{
                override fun onFailure(call: Call<SuccessUpload>, t: Throwable) {

                }

                override fun onResponse(call: Call<SuccessUpload>, response: Response<SuccessUpload>) {

                    if (response.isSuccessful){

                        Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this@MerchantRegisterActivity,MerchantProfileActivity::class.java))
                        finish()
                    }

                }
            })


        }
    }







    private fun getAllCity() {
        val call=apiService.getAllCity()
        call.enqueue(object :Callback<List<TownshipModel>>{
            override fun onFailure(call: Call<List<TownshipModel>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<TownshipModel>>, response: Response<List<TownshipModel>>)
            {
                merchant_Township=response.body()


                val adapter2 = ArrayAdapter(applicationContext,R.layout.spinner_item_row, merchant_Township)
                adapter2.setDropDownViewResource(R.layout.spinner_item_row)
                spinner_merchant_register_city.adapter=adapter2
                spinner_merchant_register_city.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                       // Toast.makeText(applicationContext,spinner_merchant_register_city.selectedItem.toString(), Toast.LENGTH_LONG).show()

                    }
                }
            }
        })    }

    private fun getAllMerchantType() {
        apiService.getAllMerchantType().enqueue(object : Callback<List<MerchantType>>{
            override fun onFailure(call: Call<List<MerchantType>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<MerchantType>>, response: Response<List<MerchantType>>) {
                if (response.isSuccessful){
                   merchantTypeList =response.body()
                    val adapter = ArrayAdapter(applicationContext,R.layout.spinner_item_row, merchantTypeList)
                    adapter.setDropDownViewResource(R.layout.spinner_item_row)
                    spinner_merchant_register_type.adapter=adapter
                    spinner_merchant_register_type.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                           // Toast.makeText(applicationContext, position.toString(), Toast.LENGTH_LONG).show()

                        }
                    }
                }
            }
        })
    }
}
