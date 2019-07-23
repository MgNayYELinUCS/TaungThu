package com.ucsmonywataungthu.org.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIInitiate  {
    companion object {

        val url="http://192.168.65.72/TaungThu/public/api/"
       //val url:String="http://192.168.65.72/bookstore_project/public/api/"
       // val url:String="https://myanbookstore.000webhostapp.com/api/"

        var retrofit:Retrofit?=null
        val client:Retrofit
        get() {
            if (retrofit==null){
                retrofit=Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

    }
}