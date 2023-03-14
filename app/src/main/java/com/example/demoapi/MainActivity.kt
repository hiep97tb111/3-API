package com.example.demoapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val httpClient = OkHttpClient.Builder()
//        httpClient.addInterceptor(logging)
//        httpClient.connectTimeout(60, TimeUnit.SECONDS)
//        httpClient.readTimeout(60, TimeUnit.SECONDS)
//
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(httpClient.build())
//            .build()
//
//        val service: APIService = retrofit.create(APIService::class.java)

        // Sử dụng @Path trong Retrofit
        tvPathAPI.setOnClickListener {
            Constant.BASE_URL = "https://jsonplaceholder.typicode.com/"
            val listCommentByPath = RetrofitClient.apiService.getListCommentByPath(1)
            listCommentByPath.enqueue(object : Callback<MutableList<User>> {
                @SuppressLint("LongLogTag")
                override fun onResponse(
                    call: Call<MutableList<User>>,
                    response: Response<MutableList<User>>
                ) {
                    Log.e("Logger listCommentByPath Success", response.body()!![0].body)
                }

                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                    Log.e("Logger listCommentByPath Fail", t.message.toString())
                }

            })
        }

        // Sử dụng @Query trong Retrofit
        tvQueryAPI.setOnClickListener {
            Constant.BASE_URL = "https://jsonplaceholder.typicode.com/"
            val listCommentByQuery = RetrofitClient.apiService.getListCommentByQuery(2)
            listCommentByQuery.enqueue(object : Callback<MutableList<User>>{
                @SuppressLint("LongLogTag")
                override fun onResponse(
                    call: Call<MutableList<User>>,
                    response: Response<MutableList<User>>
                ) {
                    Log.e("Logger listCommentByQuery Success", response.body()!![0].body)
                }

                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                    Log.e("Logger listCommentByQuery Fail", t.message.toString())
                }

            })
        }

        // Sử dụng @Post trong Retrofit
        tvPostAPI.setOnClickListener {
            Constant.BASE_URL = "https://jsonplaceholder.typicode.com/"
            val user = User(1, 101, "Test API Post", "Hiep Pham Dinh")
            val addUserByBody = RetrofitClient.apiService.addUserByBody(user)
            addUserByBody.enqueue(object : Callback<User>{
                @SuppressLint("LongLogTag")
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.e("Logger addUserByBody Success", response.body()!!.body)
                }

                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("Logger addUserByBody Fail", t.message.toString())
                }

            })

        }

        // Sử dụng @Header Authorization + @Query by Dịch Vụ Công
        tvHeaderAPI.setOnClickListener {
            Constant.BASE_URL = "http://14.248.82.173:8321/"
            val listBrief = RetrofitClient.apiService.getListBrief("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJyZXZvdGVjaCIsImlhdCI6MTY3ODc2NzE4MCwiZXhwIjoxNjc4Nzg4NzgwLCJ1c2VybmFtZSI6Imhhbmh0dC5ibnYifQ.CoqIgjzeVgBd7Ii_px4SNBhGTnGV0KP1RDmRGQtzpaBclwd_v4ZYv6QOfBxhItc9JLUTiV3Hd1nAyCVVYHHh5ceM3Nvm11SHkGcwZr2bpycjbu0TnqiAsKFM0WXZASRQBJ2jY1g-eEXIccUn9oSEOvzjpgxkWlaglytj_wnzw7NoV_G5I3cgS6VB2nFHXYeXAaqMUUrROxZgKE86_lXXIt59U8O-MwYj4vlV_4LShKq5TrCk55uED3v_lf0lMnObuSndIEEDEkZXjwTfxiG8O8WZ4EO0ZxaAQOMEZ4g0lRLLog-sL6mBo2Gy1CyBum4KcRYBEmLgDxqYzKVxOHM5hg", "240")
            listBrief.enqueue(object : Callback<Business>{
                @SuppressLint("LongLogTag")
                override fun onResponse(
                    call: Call<Business>,
                    response: Response<Business>
                ) {
                    Log.e("Logger listBrief Success", response.body()!!.success.toString())
                }

                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<Business>, t: Throwable) {
                    Log.e("Logger listBrief Fail", t.message.toString())
                }

            })
        }
    }
}