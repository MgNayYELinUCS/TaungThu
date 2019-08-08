package com.ucsmonywataungthu.org.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.QuestionAdapter

class NewsFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View =inflater.inflate(R.layout.activity_news_fragment, container, false)

       /* var newRecycler =view.findViewById<RecyclerView>(R.id.newsRecycler)
        newRecycler.layoutManager=LinearLayoutManager(context)
        newRecycler.adapter=QuestionAdapter(context)*/

        return view

    }
}