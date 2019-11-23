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
import android.util.Log
import android.widget.Toast
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.model.DailyPricePhoto
import com.ucsmonywataungthu.org.model.Knowledge
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class DailyPriceActivityNew : AppCompatActivity() {
    lateinit var apiService: APIService
    var k:List<DailyPricePhoto>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_price_new)
setSupportActionBar(daily_price_toolbar)
        daily_price_toolbar.setNavigationOnClickListener { onBackPressed() }
        val categoryList = ArrayList<DailyPricePhoto>()

        apiService= APIInitiate.client.create((APIService::class.java))
       /* categoryList.add(HomeModel(R.mipmap.plant, "စိုက္ပ်ိဴးေရး"))
        categoryList.add(HomeModel(R.mipmap.cow, "ေမြးျမဴေရး"))
        categoryList.add(HomeModel(R.mipmap.home, "ပြဲရံု"))
        categoryList.add(HomeModel(R.mipmap.knowledge, "အေထြေထြဗဟုတုတ"))
        categoryList.add(HomeModel(R.mipmap.question, "အေမးအေျဖ က႑"))
        categoryList.add(HomeModel(R.mipmap.price, "ေန႕စဥ္ကုန္စည္ေစ်း"))*/

        val sdf = SimpleDateFormat("dd-M-yyyy")
        val currentDate = sdf.format(Date())
        Log.i("Current Date",currentDate)


        daily_price_new_recycle.layoutManager = GridLayoutManager(applicationContext,1)

        apiService.getDailyPrice().enqueue(object : Callback<List<DailyPricePhoto>>{
            override fun onFailure(call: Call<List<DailyPricePhoto>>, t: Throwable) {

                Toast.makeText(this@DailyPriceActivityNew,t.message,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<DailyPricePhoto>>,
                response: Response<List<DailyPricePhoto>>
            ) {
                if (response.isSuccessful){
                     k=response.body()

                    if (!k!!.isEmpty()){
                        var adapter = DailyPriceNewAdapter(applicationContext, k!!)
                        daily_price_new_recycle.adapter = adapter
                    }
                    else{
                        Toast.makeText(this@DailyPriceActivityNew,"no list",Toast.LENGTH_LONG).show()


                    }
                }else{
                    Toast.makeText(this@DailyPriceActivityNew,"fail resp",Toast.LENGTH_LONG).show()

                }

            }


        })

        /*daily_price_datepicker.setOnClickListener{
           val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->


                    Toast.makeText(applicationContext,day.toString() + "-" + (month + 1) + "-" + year,Toast.LENGTH_SHORT).show()
apiService.getDailyPhotoByDate(day.toString() + "-" + (month + 1) + "-" + year).enqueue(object:Callback<List<DailyPricePhoto>>{
    override fun onFailure(call: Call<List<DailyPricePhoto>>, t: Throwable) {

    }

    override fun onResponse(
        call: Call<List<DailyPricePhoto>>,
        response: Response<List<DailyPricePhoto>>
    ) {
        if (response.isSuccessful){
            k=response.body()

            if (!k!!.isEmpty()){
                var adapter = DailyPriceNewAdapter(applicationContext, k!!)
                daily_price_new_recycle.adapter = adapter
            }
            else{
                Toast.makeText(this@DailyPriceActivityNew,"no list",Toast.LENGTH_LONG).show()


            }
        }else{
            Toast.makeText(this@DailyPriceActivityNew,"fail resp",Toast.LENGTH_LONG).show()

        }
    }
})


                },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis())
            datePickerDialog.show()
        }*/
    }
}
