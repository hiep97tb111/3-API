package com.example.demoapi

import retrofit2.Call
import retrofit2.http.*

interface APIService {
    // @Path
    @GET("posts/{id}/comments")
    fun getListCommentByPath(@Path("id") postId: Int): Call<MutableList<User>>

    // @Query
    @GET("comments")
    fun getListCommentByQuery(@Query("postId") postId: Int): Call<MutableList<User>>

    // @Post
    @POST("posts")
    fun addUserByBody(@Body user: User): Call<User>

    // @Header & @Query
    @GET("BusinessService/efileProcessView")
    fun getListBrief(@Header("Authorization") authorization: String, @Query("menuId") menuId: String): Call<Business>
}