package com.ucsmonywataungthu.org.Network


import com.ucsmonywataungthu.org.model.InputSuccess
import com.ucsmonywataungthu.org.model.RequestSuccess
import com.ucsmonywataungthu.org.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    fun getCropSubCategory():Call<ServerResult>

    @POST("cropCategory/{id}")

    fun getCropCategory(@Path ("id")id:Int):Call<ServerResult>

    @GET("question")
    fun getQuestion():Call<Question>

    @Multipart
    @POST("knowledge/insert")
    fun photoUpload(@Part("title") title: RequestBody,
                    @Part("description") description: RequestBody,
                    @Part photo:MultipartBody.Part):Call<SuccessUpload>

    @POST("question/{id}")
    fun getAnswer(@Path ("id")id:Int):Call<Answer>
    @POST("question/{id}")
    fun sendAnswer(@Path ("id")id:Int):Call<Answer>
}