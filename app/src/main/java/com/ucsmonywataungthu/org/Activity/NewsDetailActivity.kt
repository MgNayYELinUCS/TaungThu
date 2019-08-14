package com.ucsmonywataungthu.org.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        setSupportActionBar(news_detail_toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        news_detail_toolbar.setNavigationOnClickListener { this.onBackPressed() }

        val intent=intent
        val title=intent.getSerializableExtra("title").toString()

        tv_newsdetail_title.text=title
        tv_newsdetail_desc.text=intent.getSerializableExtra("desc").toString()
        tv_time.text=intent.getSerializableExtra("time").toString()
        val img=intent.getSerializableExtra("image").toString()
        iv_newsdetail_image
        Glide.with(this)
            .load(APIInitiate.PIC_URL+img)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(iv_newsdetail_image)

    }
}