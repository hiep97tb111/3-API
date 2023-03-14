package com.example.demoapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object{
       private val retrofitClient by lazy {
           // Logger Request & Response
           val logging = HttpLoggingInterceptor()
           logging.setLevel(HttpLoggingInterceptor.Level.BODY)
           val httpClient = OkHttpClient.Builder()
           httpClient.addInterceptor(logging)
           httpClient.connectTimeout(60, TimeUnit.SECONDS)
           httpClient.readTimeout(60, TimeUnit.SECONDS)

           // Create Retrofit
           Retrofit.Builder()
               .baseUrl(Constant.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .client(httpClient.build())
               .build()
       }

        val apiService: APIService by lazy {
            retrofitClient.create(APIService::class.java)
        }
    }
}