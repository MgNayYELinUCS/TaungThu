package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.ChooseCropAdapter
import com.ucsmonywataungthu.org.model.HomeModel

class ChooseCropActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_crop)

        val categoryList = ArrayList<HomeModel>()
        categoryList.add(HomeModel(R.mipmap.plant, "ေျမပဲ"))
        categoryList.add(HomeModel(R.mipmap.plant, "ေျပာင္းဖူး"))
        categoryList.add(HomeModel(R.mipmap.plant, "ႏွမ္း"))
        categoryList.add(HomeModel(R.mipmap.plant, "ဲ႕"))
        categoryList.add(HomeModel(R.mipmap.plant, "ေနဲႀကာ"))
        categoryList.add(HomeModel(R.mipmap.plant, "၀ါ"))

        var mainListRecycler= findViewById<RecyclerView>(R.id.choose_crop_recycle)
        mainListRecycler.layoutManager = GridLayoutManager(this,3) as RecyclerView.LayoutManager?
        var adapter = ChooseCropAdapter(this!!,categoryList)
        mainListRecycler.adapter = adapter
    }
}
