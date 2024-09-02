package com.example.practicaretrofit.api

import com.example.practicaretrofit.models.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: Long): Call<Post>

    @POST("posts")
    fun insertPost(@Body post: Post): Call<Post>
}