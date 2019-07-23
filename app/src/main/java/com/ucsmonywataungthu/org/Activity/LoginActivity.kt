package com.ucsmonywataungthu.org.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService


import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.FailLogin
import com.ucsmonywataungthu.org.model.RequestSuccess
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE)

        btn_login.setOnClickListener {
            val email=ed_email.text.toString()
            val password=ed_password.text.toString()
            LoginTask(this).execute(email,password)

        }
    }

    class LoginTask(val loginActivity: LoginActivity) : AsyncTask<String, String, String>(){

        override fun doInBackground(vararg params: String?): String? {

            val e = params[0]
            val p=params[1]
            var success: RequestSuccess
            var apiService: APIService = APIInitiate.client.create((APIService::class.java))
            Log.i("email:password",e+p)

            val call=apiService.login(e!!,p!!)
            call.enqueue(object : Callback<RequestSuccess> {

                override fun onResponse(call: Call<RequestSuccess>, response: Response<RequestSuccess>) {
                    Log.i("Response", response.toString()+response.body())

                    if (response.isSuccessful){
                        success=response.body()!!
                        Log.i("Mail", success!!.success.name+"//"+success!!.success.token)


                        Toast.makeText(loginActivity,success.success.token.toString(), Toast.LENGTH_SHORT).show()
                        loginActivity.saveToken(success!!.success.token)


                        loginActivity.finishAffinity()
                        loginActivity.startActivity(Intent(loginActivity,MainActivity::class.java))
                        loginActivity.finish()

                    }
                    else{

                        val converter= APIInitiate.client.responseBodyConverter<FailLogin>(
                            FailLogin::class.java, arrayOfNulls<Annotation>(0))
                        var errorResponse=converter.convert(response.errorBody())
                        if (errorResponse!=null){

                            Toast.makeText(loginActivity,errorResponse.error, Toast.LENGTH_SHORT).show()


                        }

                    }
                }
                override fun onFailure(call: Call<RequestSuccess>, t: Throwable) {

                    Toast.makeText(loginActivity,"no data", Toast.LENGTH_LONG).show()

                }

            })

            return "hello"
        }



        override fun onPreExecute() {

            Toast.makeText(loginActivity,"Wait", Toast.LENGTH_SHORT).show()



        }

        override fun onPostExecute(result: String?) {
            Toast.makeText(loginActivity,result, Toast.LENGTH_SHORT).show()

        }
    }
    fun saveToken(token: String) {
        var editor=sharedPreferences.edit()
        editor.putString("token",token)
        editor.commit()
    }
}
