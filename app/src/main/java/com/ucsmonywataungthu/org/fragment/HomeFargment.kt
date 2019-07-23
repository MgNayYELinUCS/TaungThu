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
        categoryList.add(HomeModel(R.mipmap.cro, "စိုက္ပ်ိဴးေရး"))
        categoryList.add(HomeModel(R.mipmap.house, "စိုက္ပ်ိဴးေရး"))
        categoryList.add(HomeModel(R.mipmap.question, "စိုက္ပ်ိဴးေရး"))
        categoryList.add(HomeModel(R.mipmap.loginacc, "စိုက္ပ်ိဴးေရး"))
        categoryList.add(HomeModel(R.mipmap.newacc, "စိုက္ပ်ိဴးေရး"))
        categoryList.add(HomeModel(R.mipmap.pice, "စိုက္ပ်ိဴးေရး"))

        var mainListRecycler= view.findViewById<RecyclerView>(R.id.homeRecycle)
        mainListRecycler.layoutManager = GridLayoutManager(context,3) as RecyclerView.LayoutManager?
        var adapter = HomeAdapter(context!!,categoryList)
        mainListRecycler.adapter = adapter
        return view

    }
}
