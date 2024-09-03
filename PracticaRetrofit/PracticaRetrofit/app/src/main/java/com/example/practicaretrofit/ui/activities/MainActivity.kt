package com.example.practicaretrofit.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaretrofit.R
import com.example.practicaretrofit.models.Post
import com.example.practicaretrofit.repositories.PostInsertListener
import com.example.practicaretrofit.repositories.PostListListener
import com.example.practicaretrofit.repositories.PostRepository
import com.example.practicaretrofit.ui.adapters.PostAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), PostListListener, PostInsertListener {
    private lateinit var lstPosts: RecyclerView
    private lateinit var fabInsertPost: FloatingActionButton

    private lateinit var btnProducto:Button
    private lateinit var btnVentas:Button
    private lateinit var btnCategoria:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnProducto= findViewById(R.id.btnProducto)
        btnCategoria= findViewById(R.id.btnCategoria)
        btnVentas= findViewById(R.id.btnVentas)

        lstPosts = findViewById(R.id.lstPosts)
        fabInsertPost = findViewById(R.id.fabInsertPost)
        setupRecyclerView()
        PostRepository.fetchListaPosts(this)
        setupEventListeners()
    }

    private fun setupEventListeners() {

        btnProducto.setOnClickListener{

            val intent = Intent(this, ProductoListActivity::class.java)
            startActivity(intent)
        }

        btnCategoria.setOnClickListener{

            val intent = Intent(this, CategoriaListActivity::class.java)
            startActivity(intent)
        }

        btnVentas.setOnClickListener{

            val intent = Intent(this, VentasListActivity::class.java)
            startActivity(intent)
        }


        fabInsertPost.setOnClickListener {
            val post = Post(1, 0, "Titulo", "Cuerpo")
            PostRepository.insertPost(post, this)
        }
    }

    private fun setupRecyclerView() {
        lstPosts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = PostAdapter(emptyList())
        }
    }

    override fun onPostListFetched(posts: List<Post>) {
        posts?.forEach { post ->
            println(post.title)
        }
        lstPosts.adapter.let { adapter ->
            if (adapter is PostAdapter) {
                adapter.updateList(posts)
            }
        }
    }

    override fun onPostListFetchError(t: Throwable) {
        t.printStackTrace()
    }

    override fun onPostInserted(post: Post) {
        Toast.makeText(this, "El id insertado es: " + post.id, Toast.LENGTH_SHORT).show()
    }

    override fun onPostInsertError(t: Throwable) {
        t.printStackTrace()
    }


}