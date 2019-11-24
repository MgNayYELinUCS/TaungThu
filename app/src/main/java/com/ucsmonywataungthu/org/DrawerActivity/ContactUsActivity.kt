package com.ucsmonywataungthu.org.DrawerActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.ContactUsAdapter
import com.ucsmonywataungthu.org.model.InfoModel

class ContactUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        setTitle("Contact Us")

        val infoList = ArrayList<InfoModel>()
        infoList.add(InfoModel(R.drawable.website_icon, "web site ကိုလာေရာက္ေလ့လာရန္"))
        infoList.add(InfoModel(R.drawable.facebook, "facebook စာမ်က္ႏွာသြားရန္"))
        infoList.add(InfoModel(R.drawable.phoneicon, "09691626426"))
        infoList.add(InfoModel(R.drawable.gmailicon, "monywacu.taungthu@gmail.com"))


        var mainListRecycler= findViewById<RecyclerView>(R.id.contactus_recycle
        )
        mainListRecycler.layoutManager = GridLayoutManager(applicationContext,1) as RecyclerView.LayoutManager?
        var adapter = ContactUsAdapter(applicationContext!!,infoList)
        mainListRecycler.adapter = adapter
    }
}
