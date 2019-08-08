package com.ucsmonywataungthu.org.Merchant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.MerchantPriceAdapter
import com.ucsmonywataungthu.org.adapter.MerchantSaleandBuyAdapter
import com.ucsmonywataungthu.org.model.MerchantModel
import com.ucsmonywataungthu.org.model.MerchantTypeModel
import kotlinx.android.synthetic.main.activity_merchant_saleand_buy.*

class MerchantSaleandBuyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_saleand_buy)
        setTitle("ေရာင္းမည္ကုန္ပစၥည္း ေရြးပါ။")

        val merchantPriceList = ArrayList<MerchantModel>()
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး", MerchantTypeModel(1,"ဆန္"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး", MerchantTypeModel(1,"ဆီ"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး",MerchantTypeModel(1,"ပဲ"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး",MerchantTypeModel(1,"ပဲ"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး",MerchantTypeModel(1,"ဆန္"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး", MerchantTypeModel(1,"ဆန္"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး", MerchantTypeModel(1,"ဆီ"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး",MerchantTypeModel(1,"ပဲ"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး",MerchantTypeModel(1,"ပဲ"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး",MerchantTypeModel(1,"ဆန္"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး", MerchantTypeModel(1,"ဆန္"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး", MerchantTypeModel(1,"ဆီ"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး",MerchantTypeModel(1,"ပဲ"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး",MerchantTypeModel(1,"ပဲ"),"","",""))
        merchantPriceList.add(MerchantModel(1,R.mipmap.plant, "စိုက္ပ်ိဴးေရး",MerchantTypeModel(1,"ဆန္"),"","",""))


        var mainListRecycler= findViewById<RecyclerView>(R.id.buyandsale_recyclerView)
        mainListRecycler.layoutManager = GridLayoutManager(applicationContext,1) as RecyclerView.LayoutManager?
        var adapter = MerchantSaleandBuyAdapter(applicationContext!!,merchantPriceList)
        mainListRecycler.adapter = adapter

    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}
