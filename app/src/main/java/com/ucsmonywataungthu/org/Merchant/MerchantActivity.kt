package com.ucsmonywataungthu.org.Merchant

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_trade_center.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.MerchantAdapter
import com.ucsmonywataungthu.org.model.CropDetailsGetAll
import com.ucsmonywataungthu.org.model.MerchantModel
import com.ucsmonywataungthu.org.model.RegionModel
import com.ucsmonywataungthu.org.model.TownshipModel
import kotlinx.android.synthetic.main.content_scrolling.*
import retrofit2.Call
import retrofit2.Response


class MerchantActivity : AppCompatActivity() {

    var apiService: APIService = APIInitiate.client.create((APIService::class.java))
    var merchantRegion:List<RegionModel>?=null
    var merchant_Township:List<TownshipModel>?=null
    var merchantList:List<MerchantModel>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade_center)
        setSupportActionBar(toolbar_trade_center)

        tradecneterrecycle.layoutManager = GridLayoutManager(applicationContext, 1)

        val callMerchant=apiService.getMerchant()
        callMerchant.enqueue(object :retrofit2.Callback<List<MerchantModel>>{
            override fun onFailure(call: Call<List<MerchantModel>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<MerchantModel>>, response: Response<List<MerchantModel>>) {
                merchantList=null
                merchantList=response.body()
                recyclerDatabind(merchantList!!)
            }
        })

        val call=apiService.getRegion()
        call.enqueue(object :retrofit2.Callback<List<RegionModel>>{
            override fun onFailure(call: Call<List<RegionModel>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<RegionModel>>, response: Response<List<RegionModel>>) {
                merchantRegion=response.body()

                val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, merchantRegion)
                //val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, merchantRegion)
                //adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner1.adapter=adapter1

                spinner1.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        bindTownship(position+1)
                    }
                }
            }
        })
    }

    fun bindTownship( position:Int){
        val call=apiService.getTownship(position)
        call.enqueue(object :retrofit2.Callback<List<TownshipModel>>{
            override fun onFailure(call: Call<List<TownshipModel>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<TownshipModel>>,response: Response<List<TownshipModel>>)
            {
                merchant_Township=response.body()

                val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, merchant_Township)
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner2.adapter=adapter2

                spinner2.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        var townshipModel=spinner2.selectedItem as TownshipModel
                        MerchantFilter(townshipModel.id)
                     }
                }
            }
        })
    }

    fun MerchantFilter(position: Int){
        val call=apiService.getMerchantFliter(position)
        call.enqueue(object :retrofit2.Callback<List<MerchantModel>>{
            override fun onFailure(call: Call<List<MerchantModel>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<MerchantModel>>, response: Response<List<MerchantModel>>) {
                merchantList=null
                merchantList=response.body()
                recyclerDatabind(merchantList!!)
             }
        })

    }

    fun recyclerDatabind(merchantList:List<MerchantModel>){
        var adapter = MerchantAdapter(applicationContext, merchantList!!)
        tradecneterrecycle.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nearby_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.nearby_menu->{
               showDialog()
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (search_trade_center.isSearchOpen()) {
            search_trade_center.closeSearch()
        } else {
            super.onBackPressed()
        }
    }
    fun showDialog(){
        val array= arrayOf("H","h")
        val builder=AlertDialog.Builder(this)
        builder.setTitle("အနီးဆံုး ပြဲရံုမ်ား")
        builder.setItems(array){dialog, which ->
            when(which){
                0->{
                    startActivity(Intent(applicationContext,MerchantSaleandBuyActivity::class.java))
                }
                1->{

                }
                2->{

                }
                3->{

                }
                4->{

                }

            }

        }
        val dialog=builder.create()
        dialog.show()
    }

}