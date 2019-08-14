package com.ucsmonywataungthu.org.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.CropPriceModel
import com.ucsmonywataungthu.org.model.DailyPriceModel

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection
import kotlinx.android.synthetic.main.activity_daily_price.*

class PriceActivity: AppCompatActivity() {


    var apiService: APIService = APIInitiate.client.create((APIService::class.java))

    lateinit var sectionedRecyclerViewAdapter:SectionedRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_price)
        this.setTitle("Daily Price")
        sectionedRecyclerViewAdapter= SectionedRecyclerViewAdapter()


        val cropPriceList=ArrayList<CropPriceModel>()
        cropPriceList.add(CropPriceModel(1,"ရက္တစ္ရာ","၁တင္း","25000"))
        cropPriceList.add(CropPriceModel(1,"ေစာရႈပ္","၁တင္း","25000"))
        cropPriceList.add(CropPriceModel(1,"မေနာ္ဆင္ဆင္","၁တင္း","35000"))
        cropPriceList.add(CropPriceModel(1,"ဧရာမင္း","၁တင္း","25000"))
        cropPriceList.add(CropPriceModel(1,"သုခၾကြယ္","၁တင္း","35000"))
        cropPriceList.add(CropPriceModel(1,"ပုလဲသြယ္","၁တင္း","28000"))
        cropPriceList.add(CropPriceModel(1,"ေပၚဆန္းေမႊး","၁တင္း","30000"))

        val cropPriceList1=ArrayList<CropPriceModel>()
        cropPriceList1.add(CropPriceModel(1,"နွံစားေျပာင္း","၁တင္း","25000"))
        cropPriceList1.add(CropPriceModel(1,"ထြက္တိုးေျပာင္း","၁တင္း","35000"))

        Log.i(cropPriceList.size.toString(),"crop sice")
        val dialyPriceList=ArrayList<DailyPriceModel>()
        dialyPriceList.add(DailyPriceModel(1,"ဆန္",cropPriceList))
        dialyPriceList.add(DailyPriceModel(2,"ေျပာင္း",cropPriceList1))


        for (list in dialyPriceList){
            sectionedRecyclerViewAdapter.addSection(PriceSection(list.crop_type,list.crop_price_model))
        }
        daily_price_recycle.layoutManager = LinearLayoutManager(this)

        daily_price_recycle.adapter=sectionedRecyclerViewAdapter
    }
}

class PriceSection : StatelessSection {

    var cl:List<CropPriceModel>

    var title:String



    constructor(title:String,list:List<CropPriceModel>) :super(
        SectionParameters.Builder(R.layout.daily_proce_item_row)
            .headerResourceId(R.layout.daily_price_row)
            .build()){
        this.cl=list
        this.title=title
    }
    override fun getContentItemsTotal(): Int {
        return cl.size
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        val itemHolder=holder as ItemViewHolder
        itemHolder.daily_trade_name.text=cl.get(position).crop_name
        itemHolder.daily_trade_price.text=cl.get(position).crop_price
        itemHolder.daily_trade_unit.text=cl.get(position).crop_unit
    }

    override fun getItemViewHolder(view: View?): RecyclerView.ViewHolder {

        return ItemViewHolder(view!!)
    }

    override fun getHeaderViewHolder(view: View?): RecyclerView.ViewHolder {
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        var headerViewHolder=holder as HeaderViewHolder
        headerViewHolder.daily_trade_type.text=title


    }



}

class HeaderViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
    val daily_trade_type=view!!.findViewById<TextView>(R.id.dail_price_title)

}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var daily_trade_name= itemView!!.findViewById<TextView>(R.id.daily_trade_name)
    var daily_trade_unit=itemView!!.findViewById<TextView>(R.id.daily_trade_unit)
    var daily_trade_price=itemView!!.findViewById<TextView>(R.id.daily_trade_price)

}
