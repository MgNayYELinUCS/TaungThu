package com.ucsmonywataungthu.org.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.HomeAdapter
import com.ucsmonywataungthu.org.model.HomeModel

class HomeFargment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View =inflater.inflate(R.layout.activity_home_fargment, container, false)

        val categoryList = ArrayList<HomeModel>()
        categoryList.add(HomeModel(R.mipmap.plant, "စိုက်ပျိုးရေး "))
        categoryList.add(HomeModel(R.mipmap.cow, "မွေးမြူရေး"))
        categoryList.add(HomeModel(R.mipmap.home, "ပွဲရုံ"))
        categoryList.add(HomeModel(R.mipmap.knowledge, "အထွေထွေဗဟုတုတ"))
        categoryList.add(HomeModel(R.mipmap.question, "အမေးအဖြေ ကဏ္ဍ"))
        categoryList.add(HomeModel(R.mipmap.price, "နေ့စဉ်ကုန်စည်ဈေး"))


        var mainListRecycler= view.findViewById<RecyclerView>(R.id.homeRecycle)
        mainListRecycler.layoutManager = GridLayoutManager(context,2) as RecyclerView.LayoutManager?
        var adapter = HomeAdapter(context!!,categoryList)
        mainListRecycler.adapter = adapter
        return view

    }
}
