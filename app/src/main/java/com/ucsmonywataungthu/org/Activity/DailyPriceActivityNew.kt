package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.DailyPriceNewAdapter
import com.ucsmonywataungthu.org.adapter.HomeAdapter
import com.ucsmonywataungthu.org.model.HomeModel
import kotlinx.android.synthetic.main.activity_daily_price_new.*
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.widget.Toast
import java.util.*


class DailyPriceActivityNew : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_price_new)

        val categoryList = ArrayList<HomeModel>()
        categoryList.add(HomeModel(R.mipmap.plant, "စိုက္ပ်ိဴးေရး"))
        categoryList.add(HomeModel(R.mipmap.cow, "ေမြးျမဴေရး"))
        categoryList.add(HomeModel(R.mipmap.home, "ပြဲရံု"))
        categoryList.add(HomeModel(R.mipmap.knowledge, "အေထြေထြဗဟုတုတ"))
        categoryList.add(HomeModel(R.mipmap.question, "အေမးအေျဖ က႑"))
        categoryList.add(HomeModel(R.mipmap.price, "ေန႕စဥ္ကုန္စည္ေစ်း"))


        daily_price_new_recycle.layoutManager = GridLayoutManager(applicationContext,1) as RecyclerView.LayoutManager?
        var adapter = DailyPriceNewAdapter(applicationContext,categoryList)
        daily_price_new_recycle.adapter = adapter

        daily_price_datepicker.setOnClickListener{
           val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, day -> Toast.makeText(applicationContext,day.toString() + "/" + (month + 1) + "/" + year,Toast.LENGTH_SHORT).show() },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis())
            datePickerDialog.show()
        }
    }
}
