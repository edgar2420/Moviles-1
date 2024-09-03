package com.example.practicarecursos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //una variable no puede ser null en kotlin
    private lateinit var lblholaMundo: TextView
    private lateinit var btnMostrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lblholaMundo = findViewById(R.id.lblHolaMundo)
        btnMostrar = findViewById(R.id.btnMostrar)
        lblholaMundo.text = getString(R.string.lblChaumundo)
        
        setupListeners()
    }

    private fun setupListeners() {
        btnMostrar.setOnClickListener{
            Toast.makeText(this@MainActivity, "Prueba toast", Toast.LENGTH_SHORT).show()
        }
    }
}