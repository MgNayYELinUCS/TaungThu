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
    fun sendmail(@Field("name")name:String,
                 @Field("email")email:String,
                 @Field("code")code:String):Call<InputSuccess>


    @POST("validate")
    @FormUrlEncoded
    fun inputValidate(@Field("name")name:String,@Field("email")email:String, @Field("password")password:String,@Field("confirm_password")confirm_password:String):Call<InputSuccess>

    @GET("cropSubCategory")
    fun getCropSubCategory():Call<ServerResult>

    @POST("cropCategory/{id}")
    fun getCropCategory(@Path("id")id:Int):Call<CropResult>

    @GET("question")
    fun getQuestion():Call<Question>

    @GET("knowledge")
    fun getKnowledge():Call<Knowledge>



    @Multipart
    @POST("knowledge/insert")
    fun photoUpload(@Part("title") title: RequestBody,
                    @Part("description") description: RequestBody,
                    @Part photo:MultipartBody.Part):Call<SuccessUpload>

    @Multipart
    @POST("newQuestion/insert")
    fun insertQuestion(@Part("question_title") question_title: RequestBody,
                       @Part("question_description") question_description: RequestBody,
                       @Part("user_id") user_id: RequestBody,
                       @Part question_photo:MultipartBody.Part?):Call<SuccessUpload>



    @POST("question/{id}")
    fun getAnswer(@Path ("id")id:Int):Call<Answer>




    @POST("answer/insert")
    @FormUrlEncoded
    fun setAnswer(@Field("answer_description")answer_description:String, @Field("user_id")user_id:Int,
                  @Field("question_id")question_id:Int):Call<SuccessUpload>


    @POST("question/{id}")
    fun sendAnswer(@Path ("id")id:Int):Call<Answer>
    @POST("cropSubCategory/{id}")
    fun getCropDetailCategory(@Path("id")id:Int):Call<CropDetailResult>

    @GET("animal")
    fun getAnimal():Call<List<AnimalModel>>

    @GET("region")
    fun getRegion():Call<List<RegionModel>>

    @POST("region/{id}")
    fun getTownship(@Path("id")id:Int):Call<List<TownshipModel>>

    @GET("allMerchant")
    fun getMerchant():Call <List<MerchantModel>>

    @POST("cityMerchant/{id}")
    fun getMerchantFliter(@Path("id")id:Int):Call <List<MerchantModel>>

    @POST("merchantPrice/{id}")
    fun getMerchantPrice(@Path("id")id:Int):Call <List<MerchantPriceModel>>

    @GET("notification")
    fun getNotification():Call <List<NotificationModel>>

    @GET("news")
    fun getNews():Call <List<News>>


}