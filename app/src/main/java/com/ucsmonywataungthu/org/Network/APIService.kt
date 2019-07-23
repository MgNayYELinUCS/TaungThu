package com.ucsmonywataungthu.org.Network


import com.ucsmonywataungthu.org.model.RequestSuccess
import retrofit2.Call
import retrofit2.http.*

interface APIService  {

    @POST("login")
    @FormUrlEncoded
    fun login(@Field("email")email:String,@Field("password")password:String):Call<RequestSuccess>

}