package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ucsmonywataungthu.org.Activity.QuestionActivity
import com.ucsmonywataungthu.org.Interface.AnswerButtonClick
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.QuestionGetAll


class QuestionAdapter(val context: Context, val qlist: List<QuestionGetAll>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    lateinit var buttonClick: AnswerButtonClick



    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        var user_profile:ImageView=view.findViewById(R.id.q_user_profile)
        var user_name:TextView=view.findViewById(R.id.q_username)
        var qdetail:TextView=view.findViewById(R.id.q_detail)
        var qimage:ImageView=view.findViewById(R.id.q_image)
        var qans:LinearLayout=view.findViewById(R.id.q_answer)
    }


    fun serOnItemClickListener(clickListener: QuestionActivity) {
        this.buttonClick=clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.question_list_row, parent, false))
    }


    override fun getItemCount(): Int {
        return qlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.user_name.text=qlist.get(position).users.name
        holder.qdetail.text=qlist.get(position).question_description
        Glide.with(context)
            .load("http://192.168.1.146/TaungThu/"+qlist.get(position).question_photo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.qimage)
        holder.qans.setOnClickListener {
            buttonClick.onAnswerClickListener(position)
        }
    }
}




