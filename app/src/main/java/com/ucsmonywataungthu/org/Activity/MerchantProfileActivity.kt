package com.ucsmonywataungthu.org.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.ucsmonywataungthu.org.Interface.CropPriceClickListener
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.MerchantPriceAdapter
import com.ucsmonywataungthu.org.model.*
import kotlinx.android.synthetic.main.activity_merchant_profile.*
import kotlinx.android.synthetic.main.activity_merchant_register.*
import kotlinx.android.synthetic.main.edit_crop_price_dialog.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MerchantProfileActivity : AppCompatActivity(),CropPriceClickListener {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_profile -> {
                updateMerchant()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    lateinit var apiService: APIService
    lateinit var cropPriceList:List<CropPriceModel>
    lateinit var sharePreferences: SharedPreferences
    var user_id:Int = 0
    var merchantId:Int = 0
    lateinit var merchantTypeList:List<MerchantType>
    lateinit var merchantModel:List<MerchantModel>
    var merchant_Township:List<TownshipModel>? = null
    var merchantRegisterActivity:MerchantRegisterActivity ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_profile)
        setSupportActionBar(toolbar_merchant_profile)
        toolbar_merchant_profile.setNavigationOnClickListener { onBackPressed() }
        sharePreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        apiService= APIInitiate.client.create((APIService::class.java))
        user_id=sharePreferences.getInt("user_id",0)


        getAllMerchantType()
        getAllCity()
        merchant_profile_recycler.layoutManager = LinearLayoutManager(applicationContext)
          apiService= APIInitiate.client.create((APIService::class.java))
          Log.i("userid",user_id.toString())

        bindMerchant(user_id)

        add_item.setOnClickListener {
            val dialog:AlertDialog=AlertDialog.Builder(this).create()
            // val dialog= AlertDialog.Builder(this)
            val view= LayoutInflater.from(this).inflate(R.layout.edit_crop_price_dialog,null)
            val name=view.ed_dialog_name
            val unit=view.ed_dialog_unit
            val price=view.ed_dialog_price
            val btnCancel=view.btn_dialog_cancel
            val btnSave=view.btn_dialog_save
            val btnAdd=view.btn_dialog_add
            btnSave.visibility=View.GONE
            btnAdd.visibility=View.VISIBLE
            btnCancel.setOnClickListener { dialog.cancel() }
            btnAdd.setOnClickListener {

                apiService.insertMerchantPrice(name.text.trim().toString(),unit.text.toString().trim(),price.text.toString().trim(),merchantId)
                    .enqueue(object :Callback<SuccessUpload>{
                        override fun onFailure(call: Call<SuccessUpload>, t: Throwable) {
                            Toast.makeText(applicationContext,"error",Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<SuccessUpload>, response: Response<SuccessUpload>) {
                            if (response.isSuccessful){
                                Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_SHORT).show()
                                bindPrice(merchantId)
                                dialog.dismiss()
                            }
                        }
                    })
            }


            dialog.setView(view)
            dialog.show()

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
                spinner_merchant_city.adapter=adapter2

            }
        })    }
    private fun bindMerchant(user_id: Int) {

        apiService.getMerchantByUser(user_id).enqueue(object : Callback<Merchant>{
            override fun onFailure(call: Call<Merchant>, t: Throwable) {
                Toast.makeText(applicationContext,"error",Toast.LENGTH_SHORT).show()
                Log.i("error",t.toString())

            }

            override fun onResponse(call: Call<Merchant>, response: Response<Merchant>) {

                if (response.isSuccessful){
                    if (!response.body()!!.merchant.isEmpty()) {
                        merchantModel= response.body()!!.merchant
                        merchantId=merchantModel[0].id
                        if (merchantModel[0]!!.status==1){
                            //as merchant
                            success_state.visibility= View.VISIBLE
                            wait_state.visibility=View.GONE

                            tv_merchant_name.setText(merchantModel[0].merchant_name)

                            tv_merchant_address.setText(merchantModel[0].merchant_address)

                            spinner_merchant_city.setSelection(merchantModel[0].city_id-1)
                            spinner_merchant_type.setSelection(merchantModel[0].merchant_type_id.toInt()-1)
                            tv_merchant_phone.setText(merchantModel[0].phone_number)

                            bindPrice(merchantId)


                        }
                        else
                        {

                            success_state.visibility= View.GONE
                            wait_state.visibility=View.VISIBLE
                        }


                    }
                    else{
                        Toast.makeText(applicationContext,"empty",Toast.LENGTH_SHORT).show()


                    }

                }
            }
        })

    }

    private fun bindPrice(merchantId: Int) {

        apiService.getMerchantPrice(merchantId).enqueue(object:Callback<List<CropPriceModel>>{
            override fun onFailure(call: Call<List<CropPriceModel>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<CropPriceModel>>, response: Response<List<CropPriceModel>>) {
                if (response.isSuccessful){
                    if (!response.body()!!.isEmpty()){
                        cropPriceList= response.body()!!
                        var adapter = MerchantPriceAdapter(applicationContext,cropPriceList)
                        merchant_profile_recycler.adapter = adapter
                        adapter.onCropPriceItemClickListener(this@MerchantProfileActivity)
                    }

                }

            }
        })
    }

    override fun onCropPriceClickListener(position: Int) {
        editAlert(position)
    }

    private fun getAllMerchantType() {
        apiService.getAllMerchantType().enqueue(object : Callback<List<MerchantType>>{
            override fun onFailure(call: Call<List<MerchantType>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<MerchantType>>, response: Response<List<MerchantType>>) {
                if (response.isSuccessful){
                    merchantTypeList= response.body()!!
                    val adapter = ArrayAdapter(applicationContext,R.layout.spinner_item_row, merchantTypeList)
                    adapter.setDropDownViewResource(R.layout.spinner_item_row)
                    spinner_merchant_type.adapter=adapter
                }
            }
        })
    }
    private fun editAlert(position: Int) {
        val dialog:AlertDialog=AlertDialog.Builder(this).create()
        // val dialog= AlertDialog.Builder(this)
        val view= LayoutInflater.from(this).inflate(R.layout.edit_crop_price_dialog,null)
        val name=view.ed_dialog_name
        val unit=view.ed_dialog_unit
        val price=view.ed_dialog_price
        val btnCancel=view.btn_dialog_cancel
        val btnSave=view.btn_dialog_save

        name.setText(cropPriceList.get(position).crop_name)
        price.setText(cropPriceList.get(position).crop_price)
        unit.setText(cropPriceList.get(position).crop_unit)
        btnCancel.setOnClickListener { dialog.cancel() }
        btnSave.setOnClickListener {
            if (name.text.equals(""))
                name.setError("Enter Crop Name")
            else if(unit.text.equals(""))
                unit.setError("Enter Crop Unit")
            else if(price.text.equals(""))
                price.setError("Enter Crop Price")
            else {


                apiService.updateMerchantPrice(
                    cropPriceList.get(position).id,
                    name.text.trim().toString(),
                    unit.text.toString().trim(),
                    price.text.toString().trim()
                )
                    .enqueue(object : Callback<SuccessUpload> {
                        override fun onFailure(call: Call<SuccessUpload>, t: Throwable) {
                            Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()

                        }

                        override fun onResponse(
                            call: Call<SuccessUpload>,
                            response: Response<SuccessUpload>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    response.body()!!.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                                bindPrice(merchantId)
                                dialog.dismiss()

                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "not successful",
                                    Toast.LENGTH_SHORT
                                ).show()


                            }
                        }
                    })
            }

        }
        dialog.setView(view)
        dialog.show()
    }

    private fun updateMerchant() {

        val name=tv_merchant_name.text.toString().trim()
        val address=tv_merchant_address.text.toString().trim()
        val phone=tv_merchant_phone.text.toString().trim()
        val type_id=merchantTypeList.get(spinner_merchant_type.selectedItemPosition)!!.id
        val city_id=merchant_Township!!.get(spinner_merchant_city.selectedItemPosition)!!.id
//        Toast.makeText(applicationContext,type_id.toString()+" "+city_id.toString(),Toast.LENGTH_SHORT).show()

        if (name.equals("")){
            tv_merchant_name.setError("Enter name")
        }
        else if (address.equals("")){
            tv_merchant_address.setError("Enter address")
        }
        else if (phone.equals("")){
            tv_merchant_phone.setError("Enter phone")
        }
        else{
            //Toast.makeText(applicationContext,merchantId.toString()+""+name+""+type_id+""+address+""+phone+""+city_id,Toast.LENGTH_LONG).show()

            apiService.updateMerchantProfile(merchantId,name,type_id,address,phone,city_id)
                .enqueue(object:Callback<SuccessUpload>{
                    override fun onFailure(call: Call<SuccessUpload>, t: Throwable) {
                        Toast.makeText(applicationContext,t.toString(),Toast.LENGTH_SHORT).show()

                    }

                    override fun onResponse(call: Call<SuccessUpload>, response: Response<SuccessUpload>) {
                        if (response.isSuccessful){
                            Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_LONG).show()


                            // finish()

                        }
                    }
                })
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@MerchantProfileActivity,MainActivity::class.java))
        finishAffinity()
    }
}
