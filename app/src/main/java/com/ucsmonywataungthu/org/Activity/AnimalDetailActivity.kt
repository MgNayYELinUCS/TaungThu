package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import kotlinx.android.synthetic.main.activity_animal_detail.*
import me.myatminsoe.mdetect.MDetect
import me.myatminsoe.mdetect.Rabbit

class AnimalDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_detail)
        setSupportActionBar(toolbar_animal_detail)
        toolbar_animal_detail.setNavigationOnClickListener { onBackPressed() }
        val intent=intent

        Glide.with(applicationContext).load(APIInitiate.PIC_URL+intent.getSerializableExtra("animalpicture").toString())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(animal_detail_pic)
        //Toast.makeText(applicationContext,APIInitiate.PIC_URL+intent.getSerializableExtra("animalpicture").toString(),Toast.LENGTH_SHORT).show()
        if(MDetect.isUnicode()){
            supportActionBar!!.title=intent.getSerializableExtra("animalname").toString()+" အကြောင်း"
            animal_detail_name.text=intent.getSerializableExtra("animalname").toString()
            animal_detail_des.text=intent.getSerializableExtra("animaldesc").toString()
        }else{
            supportActionBar!!.title=Rabbit.uni2zg(intent.getSerializableExtra("animalname").toString()+" အကြောင်း")
            animal_detail_name.text = Rabbit.uni2zg(intent.getSerializableExtra("animalname").toString())
            animal_detail_des.text = Rabbit.uni2zg(intent.getSerializableExtra("animaldesc").toString())
        }


    }
}
