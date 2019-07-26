package com.ucsmonywataungthu.org.Network


import com.ucsmonywataungthu.org.model.InputSuccess
import com.ucsmonywataungthu.org.model.RequestSuccess
import com.ucsmonywataungthu.org.model.*
import retrofit2.Call
import retrofit2.http.*

interface APIService  {
    @POST("login")
    @FormUrlEncoded
    fun login(@Field("email")email:String, @Field("password")password:String):Call<RequestSuccess>

    @POST("register")
    @FormUrlEncoded
    fun register(@Field("name")name:String,@Field("email")email:String, @Field("password")password:String,@Field("confirm_password")confirm_password:String):Call<RequestSuccess>


    @POST("sendmail")
    @FormUrlEncoded
    fun sendmail(@Field("name")name:String, @Field("email")email:String,@Field("code")code:String):Call<InputSuccess>


    @POST("validate")
    @FormUrlEncoded
    fun inputValidate(@Field("name")name:String,@Field("email")email:String, @Field("password")password:String,@Field("confirm_password")confirm_password:String):Call<InputSuccess>

    @GET("cropSubCategory")
    fun getSubCategory():Call<ServerResult>

    @POST("cropCategory/{id}")
    @FormUrlEncoded
    fun getCropCategory(@Path ("id")id:Int):Call<ServerResult>

}