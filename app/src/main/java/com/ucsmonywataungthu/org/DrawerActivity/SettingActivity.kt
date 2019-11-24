package com.ucsmonywataungthu.org.DrawerActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.SettingAdapter
import com.ucsmonywataungthu.org.model.InfoModel

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setTitle("Setting")

        val infoList = ArrayList<InfoModel>()
        infoList.add(InfoModel(R.mipmap.plant, "ဘာသာစကား"))
        infoList.add(InfoModel(R.mipmap.plant, "အသံုးျပဳပံု"))
        infoList.add(InfoModel(R.mipmap.plant, "ထြက္ရန္"))


        var mainListRecycler= findViewById<RecyclerView>(R.id.setting_recycle)
        mainListRecycler.layoutManager = GridLayoutManager(applicationContext,1) as RecyclerView.LayoutManager?
        var adapter = SettingAdapter(applicationContext!!,infoList)
        mainListRecycler.adapter = adapter
    }
}
