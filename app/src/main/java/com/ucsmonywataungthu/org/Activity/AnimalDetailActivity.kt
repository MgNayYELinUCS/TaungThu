package com.ucsmonywataungthu.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.R
import kotlinx.android.synthetic.main.activity_animal_detail.*

class AnimalDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_detail)

        val intent=intent

        this.setTitle(intent.getSerializableExtra("animalname").toString()+" အေၾကာင္း")
        Glide.with(applicationContext).load(APIInitiate.PIC_URL+intent.getSerializableExtra("animalpicture").toString())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(animal_detail_pic)
        //Toast.makeText(applicationContext,APIInitiate.PIC_URL+intent.getSerializableExtra("animalpicture").toString(),Toast.LENGTH_SHORT).show()
        animal_detail_name.text=intent.getSerializableExtra("animalname").toString()
        animal_detail_des.text=intent.getSerializableExtra("animaldesc").toString()

    }
}
