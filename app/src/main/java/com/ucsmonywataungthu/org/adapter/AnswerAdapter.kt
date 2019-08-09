package com.ucsmonywataungthu.org.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.AnswerGetOneQuestion

class AnswerAdapter(val context: Context, val ansList: List<AnswerGetOneQuestion>) : RecyclerView.Adapter<AnsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnsViewHolder {

        return AnsViewHolder(LayoutInflater.from(context).inflate(R.layout.answer_list_row, parent, false))

    }

    override fun getItemCount(): Int {
        return ansList.size
    }

    override fun onBindViewHolder(holder: AnsViewHolder, position: Int) {
        holder.a_username.text=ansList[position].users.name
        holder.a_diss.text=ansList[position].answer_description
    }

}

class AnsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var a_username:TextView=view.findViewById(R.id.ans_user_name)
    var a_diss:TextView=view.findViewById(R.id.ans_diss)

}

