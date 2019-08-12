package com.ucsmonywataungthu.org.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.poovam.pinedittextfield.LinePinField
import com.poovam.pinedittextfield.PinField
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.InputSuccess
import com.ucsmonywataungthu.org.model.RequestSuccess
import kotlinx.android.synthetic.main.activity_verify_email.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class VerifyEmailActivity : AppCompatActivity() {


    lateinit var sharedPreferences: SharedPreferences

    lateinit var code:String
    lateinit var inputCode:String

    lateinit var name:String
    lateinit var email:String
    lateinit var password:String
    lateinit var confirm_password:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_email)

        sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val intent=intent
        name=intent.getSerializableExtra("name").toString()
        email=intent.getSerializableExtra("email").toString()

        password=intent.getSerializableExtra("password").toString()

        confirm_password=intent.getSerializableExtra("confirm_password").toString()
        user_email.text=email


        sendingCode()
        var linepin: LinePinField = findViewById(R.id.lineField)
        linepin.onTextCompleteListener=object :PinField.OnTextCompleteListener{
            override fun onTextComplete(enteredText: String): Boolean {
                    btn_verify.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                    btn_verify.setTextColor(resources.getColor(android.R.color.white))
                    btn_verify.isEnabled=true
                    inputCode=enteredText

                return true
            }

        }



        btn_verify.setOnClickListener {
            Toast.makeText(this@VerifyEmailActivity, inputCode, Toast.LENGTH_LONG).show()
            register()
        }
        btn_resend.setOnClickListener {
            sendingCode()
        }
    }

    private fun sendingCode() {
        btn_resend.isEnabled=false
        sendingTimer()
        val random=Random
        code=String.format("%04d", random.nextInt(10000))
        var apiService: APIService = APIInitiate.client.create((APIService::class.java))

        val call = apiService.sendmail(name!!, email!!,code!!)
        call.enqueue(object : Callback<InputSuccess> {
            override fun onFailure(call: Call<InputSuccess>, t: Throwable) {

                Toast.makeText(this@VerifyEmailActivity, "Connection Lose.!! Try again", Toast.LENGTH_LONG).show()

            }
            override fun onResponse(call: Call<InputSuccess>, response: Response<InputSuccess>) {
                Log.i("Response", response.toString() + response.body())

                if (response.isSuccessful) {
                    val success = response.body()!!
                    Log.i("Valid", success!!.success)
                    Toast.makeText(this@VerifyEmailActivity,"Sending......", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun sendingTimer() {
        object: CountDownTimer(120000,1000){
            override fun onFinish() {
                btn_resend.isEnabled=true
                Toast.makeText(applicationContext,"complete timer",Toast.LENGTH_SHORT).show()
            }

            override fun onTick(millisUntilFinished: Long) {
                var millisUntilFinished=millisUntilFinished
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                tv_coundown.text=minutes.toString()+":"+seconds.toString()
            }

        }.start()
    }

    private fun register() {


        if (inputCode.equals(code)){
            Toast.makeText(this,"correct",Toast.LENGTH_SHORT).show()
            /* val registerTask=RegisterTask(this)
             registerTask.execute(email,password,name,confirm_password)*/
            var apiService: APIService = APIInitiate.client.create((APIService::class.java))
            Log.i("email:password", email + password)

            val call = apiService.register(name!!, email!!, password!!, confirm_password!!)
            call.enqueue(object : Callback<RequestSuccess> {
                override fun onFailure(call: Call<RequestSuccess>, t: Throwable) {

                    Toast.makeText(this@VerifyEmailActivity, "no data", Toast.LENGTH_LONG).show()

                }

                override fun onResponse(call: Call<RequestSuccess>, response: Response<RequestSuccess>) {
                    Log.i("Response", response.toString() + response.body())

                    if (response.isSuccessful) {
                        val success = response.body()!!
                        Log.i("Mail", success!!.success.name + "//" + success!!.success.token)

                        Toast.makeText(this@VerifyEmailActivity, "Success", Toast.LENGTH_SHORT).show()
                        saveUser(success!!.success.token,success!!.success.id)
                        finishAffinity()

                        startActivity(Intent(this@VerifyEmailActivity, MainActivity::class.java))
                        finish()


                    }
                }

            })
        }
        else{
            Toast.makeText(this@VerifyEmailActivity, "Wrong code!! Check your email", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveUser(token: String, id: Int) {
        var editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.putInt("user_id",id)
        editor.commit()

    }
}
