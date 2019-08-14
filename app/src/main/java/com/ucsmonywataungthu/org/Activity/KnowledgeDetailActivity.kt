package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import kotlinx.android.synthetic.main.activity_knowledge_detail.*

class KnowledgeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledge_detail)
        setSupportActionBar(knowledge_detail_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        knowledge_detail_toolbar.setNavigationOnClickListener { this.onBackPressed() }

        val intent=intent
        val title=intent.getSerializableExtra("title").toString()
        tv_knowledge_detail_title.text=title
        tv_knowledge_detail_desc.text=intent.getSerializableExtra("desc").toString()
        tv_knowledge_time.text=intent.getSerializableExtra("time").toString()
        val img=intent.getSerializableExtra("image").toString()

        Glide.with(this)
            .load(APIInitiate.PIC_URL+img)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(iv_knowledge_detail_image)
    }
}
