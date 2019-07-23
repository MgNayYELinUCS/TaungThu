package com.ucsmonywataungthu.org

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.adapter.AnimalHusbandryAdapter
import com.ucsmonywataungthu.org.adapter.ChooseCropAdapter
import com.ucsmonywataungthu.org.model.HomeModel

class AnimalHusbandry : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_husbandry)

        val categoryList = ArrayList<HomeModel>()
        categoryList.add(HomeModel(R.mipmap.cow, "ေျမပဲ"))
        categoryList.add(HomeModel(R.mipmap.cow, "ေျပာင္းဖူး"))
        categoryList.add(HomeModel(R.mipmap.cow, "ႏွမ္း"))
        categoryList.add(HomeModel(R.mipmap.cow, "ဲ႕"))
        categoryList.add(HomeModel(R.mipmap.cow, "ေနဲႀကာ"))
        categoryList.add(HomeModel(R.mipmap.cow, "၀ါ"))

        var mainListRecycler= findViewById<RecyclerView>(R.id.choose_crop_recycle)
        mainListRecycler.layoutManager = GridLayoutManager(this,3) as RecyclerView.LayoutManager?
        var adapter = AnimalHusbandryAdapter(this!!,categoryList)
        mainListRecycler.adapter = adapter
    }
}
