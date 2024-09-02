package com.example.practicaretrofit.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.practicaretrofit.models.Post
import com.example.practicaretrofit.repositories.PostInsertListener
import com.example.practicaretrofit.repositories.PostListListener
import com.example.practicaretrofit.repositories.PostRepository
import com.example.practicaretrofit.ui.adapters.PostAdapter
import com.example.practico4.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(){


    private lateinit var btnProducto:Button
    private lateinit var btnVentas:Button
    private lateinit var btnCategoria:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnProducto= findViewById(R.id.btnProducto)
        btnCategoria= findViewById(R.id.btnCategoria)
        btnVentas= findViewById(R.id.btnVentas)
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

    }



}