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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_price)
        this.setTitle("ေန႕စဥ္ကုန္စည္ေစ်းႏႈနး")

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

    }
}
