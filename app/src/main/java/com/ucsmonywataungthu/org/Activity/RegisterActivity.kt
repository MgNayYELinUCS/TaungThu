package com.ucsmonywataungthu.org.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.InputFail
import com.ucsmonywataungthu.org.model.InputSuccess
import com.ucsmonywataungthu.org.model.RequestSuccess
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {



    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE)



        btn_register.setOnClickListener {
            val name=ed_register_name.text.toString()
            val email=ed_register_email.text.toString()
            val password=ed_register_password.text.toString()
            val confirm_password=ed_register_confirm_password.text.toString()
            //RegisterTask(this).execute(name,email,password,confirm_password)
            var apiService: APIService = APIInitiate.client.create((APIService::class.java))
            Log.i("email:password", email + password)

            /*val call = apiService.inputValidate(name!!, email!!, password!!, confirm_password!!)
            call.enqueue(object : Callback<InputSuccess> {
                override fun onFailure(call: Call<InputSuccess>, t: Throwable) {

                    Toast.makeText(this@RegisterActivity, "Connection Lose.!! Try again", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<InputSuccess>, response: Response<InputSuccess>) {
                    Log.i("Response", response.toString() + response.body())

                    if (response.isSuccessful) {
                        val success = response.body()!!
                        Log.i("Valid", success!!.success)

                        Toast.makeText(this@RegisterActivity, success!!.success, Toast.LENGTH_SHORT).show()
                        //finishAffinity()

                        //val intent= Intent(this@RegisterActivity, MainActivity::class.java)
                        *//*val intent= Intent(this@RegisterActivity, VerifyEmailActivity::class.java)
                        intent.putExtra("name",name)
                        intent.putExtra("email",email)
                        intent.putExtra("password",password)
                        intent.putExtra("confirm_password",confirm_password)*//*
                        startActivity(intent)
                        //finish()


                    } else {


                        val converter=APIInitiate.client.responseBodyConverter<InputFail>(
                            InputFail::class.java, arrayOfNulls<Annotation>(0))
                        var errorResponse=converter.convert(response.errorBody())
                        if (errorResponse!=null){
                            if (errorResponse.error.name!=null){
                                this@RegisterActivity.ed_register_name.error=errorResponse.error.name.get(0)
                            }
                            if (errorResponse.error.email!=null){
                                this@RegisterActivity.ed_register_email.error=errorResponse.error.email.get(0)
                            }
                            if (errorResponse.error.password!=null){
                                this@RegisterActivity.ed_register_password.error=errorResponse.error.password.get(0)
                            }
                            if (errorResponse.error.confirm_password!=null){
                                this@RegisterActivity.ed_register_confirm_password.error=errorResponse.error.confirm_password.get(0)
                            }

                        }

                    }
                }

            })*/

            val call = apiService.register(name, email, password, confirm_password)
            call.enqueue(object : Callback<RequestSuccess> {
                override fun onFailure(call: Call<RequestSuccess>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "no data", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<RequestSuccess>, response: Response<RequestSuccess>) {
                    Log.i("Response", response.toString() + response.body())

                    if (response.isSuccessful) {
                        val success = response.body()!!
                        Log.i("Mail", success.success.name + "//" + success.success.token)

                        Toast.makeText(this@RegisterActivity, "Success", Toast.LENGTH_SHORT).show()
                        saveUser(success.success.token,success.success.id,success.success.role,success.success.name)
                        finishAffinity()

                        startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                        finish()
                    }
                    else{

                        val converter=APIInitiate.client.responseBodyConverter<InputFail>(
                            InputFail::class.java, arrayOfNulls<Annotation>(0))
                        var errorResponse=converter.convert(response.errorBody())
                        if (errorResponse!=null){
                            if (errorResponse.error.name!=null){
                                this@RegisterActivity.ed_register_name.error=errorResponse.error.name.get(0)
                            }
                            if (errorResponse.error.email!=null){
                                this@RegisterActivity.ed_register_email.error=errorResponse.error.email.get(0)
                            }
                            if (errorResponse.error.password!=null){
                                this@RegisterActivity.ed_register_password.error=errorResponse.error.password.get(0)
                            }
                            if (errorResponse.error.confirm_password!=null){
                                this@RegisterActivity.ed_register_confirm_password.error=errorResponse.error.confirm_password.get(0)
                            }

                        }
                        Log.i("Response is not success",response.body().toString())


                    }
                }

            })

        }
    }

    fun saveUser(token: String, id: Int, role: String, name: String) {
        var editor=sharedPreferences.edit()
        editor.putString("token",token)
        editor.putInt("user_id",id)
        editor.putString("user_name",name)
        editor.putString("role",role)
        editor.commit()
    }
}
