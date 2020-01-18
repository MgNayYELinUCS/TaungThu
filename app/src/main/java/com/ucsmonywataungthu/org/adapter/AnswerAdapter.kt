package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.AnswerGetOneQuestion
import me.myatminsoe.mdetect.MDetect
import me.myatminsoe.mdetect.Rabbit

class AnswerAdapter(val context: Context, val ansList: List<AnswerGetOneQuestion>) : RecyclerView.Adapter<AnsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnsViewHolder {

        return AnsViewHolder(LayoutInflater.from(context).inflate(R.layout.answer_list_row, parent, false))

    }

    override fun getItemCount(): Int {
        return ansList.size
    }

    override fun onBindViewHolder(holder: AnsViewHolder, position: Int) {

        if(MDetect.isUnicode()){
            holder.a_username.text=ansList[position].users.name.toString()
            holder.a_diss.text=ansList[position].answer_description.toString()
        }else{
            holder.a_username.text= Rabbit.uni2zg(ansList[position].users.name.toString())
            holder.a_diss.text=Rabbit.uni2zg(ansList[position].answer_description.toString())
        }

        if (ansList[position].users.role=="expert"){
            Log.i("**This is expert",position.toString()+ansList[position].answer_description)
            holder.star.setImageResource(R.drawable.star_fill)
        }
        else{
            Log.i("**This is not expert",position.toString()+ansList[position].answer_description)
        }
    }

}

class AnsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var a_username:TextView=view.findViewById(R.id.ans_user_name)
    var a_diss:TextView=view.findViewById(R.id.ans_diss)

    var star:ImageView=view.findViewById(R.id.star)

}

