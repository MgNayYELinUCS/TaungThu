package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ucsmonywataungthu.org.Activity.KnowledgeDetailActivity
import com.ucsmonywataungthu.org.Activity.NewsDetailActivity
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.Knowledge
import com.ucsmonywataungthu.org.model.KnowledgeGetAll
import com.ucsmonywataungthu.org.model.News


class KnowledgeAdapter(val context: Context, val knowledgeList:List<KnowledgeGetAll>) : RecyclerView.Adapter<KnowledgeViewModel>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnowledgeViewModel {
        return  KnowledgeViewModel(LayoutInflater.from(parent.context).inflate(R.layout.knowledge_list_row, parent, false))

    }

    override fun getItemCount(): Int {
        return knowledgeList!!.size
    }

    override fun onBindViewHolder(holder: KnowledgeViewModel, position: Int) {
        holder.k_time.text=knowledgeList.get(position).created_at
        holder.k_title.text=knowledgeList.get(position).title
       // holder.k_desc.text=knowledgeList.get(position).description
        Glide.with(context!!)
            .load(APIInitiate.PIC_URL+knowledgeList.get(position).photo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.k_image)

        //holder.k_option.setOnClickListener {
          //  holder.k_desc.width=WindowManager.LayoutParams.WRAP_CONTENT



        //}
        holder.k_row.setOnClickListener {
            val intent=Intent(context,KnowledgeDetailActivity::class.java)
            intent.putExtra("title",knowledgeList.get(position).title)
            intent.putExtra("desc",knowledgeList.get(position).description)
            intent.putExtra("image",knowledgeList.get(position).photo)
            intent.putExtra("time",knowledgeList.get(position).created_at)
            context.startActivity(intent)
        }
    }
}

class KnowledgeViewModel (view: View): RecyclerView.ViewHolder(view) {
    var k_image: ImageView =view.findViewById(R.id.knowledge_image)
    var  k_title: TextView =view.findViewById(R.id.knowledge_title)
   // var k_desc:TextView=view.findViewById(R.id.knowledge_description)
    var k_time: TextView =view.findViewById(R.id.knowledge_created_time)
    //var k_option:ImageButton=view.findViewById(R.id.knowledge_option)
    var k_row: RelativeLayout =view.findViewById(R.id.knowledge_row)


}