package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.AnimalHusbandryAdapter
import com.ucsmonywataungthu.org.adapter.ChooseCropAdapter
import com.ucsmonywataungthu.org.model.AnimalModel
import com.ucsmonywataungthu.org.model.CropResult
import com.ucsmonywataungthu.org.model.CropSubcategory
import com.ucsmonywataungthu.org.model.HomeModel
import kotlinx.android.synthetic.main.activity_animal_husbandry.*
import kotlinx.android.synthetic.main.activity_choose_crop.*
import retrofit2.Call
import retrofit2.Response

class AnimalHusbandryActivity : AppCompatActivity() {
    internal var api: APIService? = null
    var apiService: APIService = APIInitiate.client.create((APIService::class.java))
    var animalList:List<AnimalModel>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_husbandry)
        setSupportActionBar(toolbar_animal)
        setTitle(null)
        toolbar_animal.setNavigationOnClickListener { this.onBackPressed() }

        var mainListRecycler= findViewById<RecyclerView>(R.id.animalhusrecycler)
        mainListRecycler.layoutManager = GridLayoutManager(this,2) as RecyclerView.LayoutManager?


        val call=apiService.getAnimal()
        call.enqueue(object : retrofit2.Callback<List<AnimalModel>> {
            override fun onFailure(call: Call<List<AnimalModel>>, t: Throwable) {
                Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<AnimalModel>>, response: Response<List<AnimalModel>>) {
                //cropList=null
                animalList=response!!.body()
                var adapter = AnimalHusbandryAdapter(applicationContext, animalList!!)
                mainListRecycler.adapter = adapter
            }
        })
    }
}
