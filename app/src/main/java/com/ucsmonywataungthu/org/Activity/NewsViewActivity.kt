package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ucsmonywataungthu.org.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_news_view.*
import kotlinx.android.synthetic.main.news_detail_collasping_content.*

class NewsViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_view)
        setSupportActionBar(news_toolbar)

        val intent=intent
        news_detail_img.setImageResource(intent.getIntExtra("news_image",0))
        news_detail_title.text=intent.getStringExtra("news_title")
        news_detail_des.text=intent.getStringExtra("news_des")

    }
}
