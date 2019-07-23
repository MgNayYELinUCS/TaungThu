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
        categoryList.add(HomeModel(R.mipmap.plant, "စိုက္ပ်ိဴးေရး"))
        categoryList.add(HomeModel(R.mipmap.cow, "ေမြးျမဴေရး"))
        categoryList.add(HomeModel(R.mipmap.home, "ပြဲရံု"))
        categoryList.add(HomeModel(R.mipmap.knowledge, "အေထြေထြဗဟုတုတ"))
        categoryList.add(HomeModel(R.mipmap.question, "အေမးအေျဖ က႑"))
        categoryList.add(HomeModel(R.mipmap.price, "ေန႕စဥ္ကုန္စည္ေစ်း"))


        var mainListRecycler= view.findViewById<RecyclerView>(R.id.homeRecycle)
        mainListRecycler.layoutManager = GridLayoutManager(context,3) as RecyclerView.LayoutManager?
        var adapter = HomeAdapter(context!!,categoryList)
        mainListRecycler.adapter = adapter
        return view

    }
}
