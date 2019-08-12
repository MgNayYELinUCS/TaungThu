package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.DailyPriceAdapter
import com.ucsmonywataungthu.org.model.DailyPriceModel
import com.ucsmonywataungthu.org.model.RegionModel
import com.ucsmonywataungthu.org.model.TownshipModel
import kotlinx.android.synthetic.main.activity_daily_price.*
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ucsmonywataungthu.org.model.CropPriceModel

class DailyPriceActivity : AppCompatActivity() {
    var apiService: APIService = APIInitiate.client.create((APIService::class.java))
    var daily_price_Region:List<RegionModel>?=null
    var daily_price_Township:List<TownshipModel>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_price)
        this.setTitle("Daily Price")

        val cropPriceList=ArrayList<CropPriceModel>()
        cropPriceList.add(CropPriceModel(1,"nay ffffffffffff","1 thin","5000"))
        cropPriceList.add(CropPriceModel(1,"seccond","1 thin","5000"))
        cropPriceList.add(CropPriceModel(1,"gggg char","1 thin","5000"))

        Log.i(cropPriceList.size.toString(),"crop sice")
        val dialyPriceList=ArrayList<DailyPriceModel>()
        dialyPriceList.add(DailyPriceModel(1,"Oil Crops",cropPriceList))


        daily_price_recycle.layoutManager = LinearLayoutManager(applicationContext)
        var adapter = DailyPriceAdapter(applicationContext,dialyPriceList)
        daily_price_recycle.adapter = adapter

        val adapterregion = ArrayAdapter.createFromResource(
            this,
            com.ucsmonywataungthu.org.R.array.Animals, R.layout.spinner_item_row
        )
        adapterregion.setDropDownViewResource(R.layout.spinner_item_row);
        daily_price_spinner_region.adapter=adapterregion
        daily_price_spinner_township.adapter=adapterregion
        val options = applicationContext.getResources().getStringArray(R.array.Animals);
        daily_price_spinner_region.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(applicationContext, " You select >> "+options[position], Toast.LENGTH_SHORT).show()
            }

        }
    }
}
