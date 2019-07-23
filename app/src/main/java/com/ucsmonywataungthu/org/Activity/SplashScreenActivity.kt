package com.ucsmonywataungthu.org.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.ucsmonywataungthu.org.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        object:CountDownTimer(2000,1000){
            override fun onFinish() {
                val intent=Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()

            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }
}
