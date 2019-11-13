package com.ucsmonywataungthu.org.Network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIInitiate  {

    companion object {

        /*val BASE_URL = "http://192.168.43.250/TaungThu/api/"
        val PIC_URL = "http://192.168.43.250/TaungThu/"*/
        val BASE_URL = "https://taungthu.000webhostapp.com/api/"
        val PIC_URL = "https://taungthu.000webhostapp.com/"
        var retrofit: Retrofit? = null
        val client: Retrofit
            get() {
                if (retrofit == null) {

                    val okHttpClient = OkHttpClient.Builder()
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build()

                    val gson = GsonBuilder()
                        .setLenient()
                        .create()

                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                }
                return retrofit!!
            }
    }
}