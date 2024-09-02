package com.example.practicaretrofit.repositories

import com.example.practicaretrofit.api.PostService
import com.example.practicaretrofit.models.Post
import com.example.practicaretrofit.ui.adapters.PostAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostRepository {
    fun fetchListaPosts(listener: PostListListener) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val postService = retrofit.create(PostService::class.java)
        postService.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()
                    posts?.let {
                        listener.onPostListFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                listener.onPostListFetchError(t)
            }
        })
    }

    fun insertPost(post: Post, listener: PostInsertListener) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val postService = retrofit.create(PostService::class.java)
        postService.insertPost(post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    post?.let {
                        listener.onPostInserted(it)
                    }
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                listener.onPostInsertError(t)
            }
        })
    }
}

interface PostListListener {
    fun onPostListFetched(posts: List<Post>)
    fun onPostListFetchError(t: Throwable)
}

interface PostInsertListener {
    fun onPostInserted(post: Post)
    fun onPostInsertError(t: Throwable)
}