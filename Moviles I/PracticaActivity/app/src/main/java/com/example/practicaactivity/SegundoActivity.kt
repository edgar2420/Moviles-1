package com.example.practicaactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SegundoActivity : AppCompatActivity() {
    private lateinit var lblWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segundo)
        lblWelcome = findViewById(R.id.lblWelcome)

        val username = intent.getStringExtra("username")
        if (username != null){
            lblWelcome.text = "Bienvenido $username"
        } else {
            lblWelcome.text = "Ingrese un usario en la pantalla anterior"
        }
    }
}