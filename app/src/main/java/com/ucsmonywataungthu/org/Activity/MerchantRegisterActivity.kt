package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.ucsmonywataungthu.org.R
import kotlinx.android.synthetic.main.activity_merchant_register.*

class MerchantRegisterActivity : AppCompatActivity() {
    var languages = arrayOf("English", "French", "Spanish", "Hindi", "Russian", "Telugu", "Chinese", "German", "Portuguese", "Arabic", "Dutch", "Urdu", "Italian", "Tamil", "Persian", "Turkish", "Other")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_register)

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_merchant_register_city!!.setAdapter(aa)
        spinner_merchant_register_type!!.setAdapter(aa)
    }

}
