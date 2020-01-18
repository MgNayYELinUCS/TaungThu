package com.ucsmonywataungthu.org.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.ucsmonywataungthu.org.R
import me.myatminsoe.mdetect.MDetect

class SplashScreenActivity : AppCompatActivity() {
    lateinit var sharePreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        MDetect.init(this)
        sharePreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE)

        object:CountDownTimer(2000,1000){
            override fun onFinish() {
                if (sharePreferences.contains("token")){

                    var intent=Intent(this@SplashScreenActivity,MainActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()


                }else {
                    var intent=Intent(this@SplashScreenActivity,LoginActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }

            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }
}
