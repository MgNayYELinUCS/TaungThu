package com.ucsmonywataungthu.org.Merchant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.DailyPriceModel
import kotlinx.android.synthetic.main.activity_merchant_listand_price.*

class MerchantDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_listand_price)

        val intent=intent
        setTitle(intent.getSerializableExtra("mname").toString())
        txt_merchant_main_name.text=intent.getSerializableExtra("mname").toString()
        //txt_merchant_main_type.text=intent.getSerializableExtra("mtype").toString()
        txt_merchant_main_address.text=intent.getSerializableExtra("maddress").toString()
        txt_merchant_main_phone.text=intent.getSerializableExtra("mphone").toString()


    }
}
