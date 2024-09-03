package com.example.practicaactivityintents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DatosActivity : AppCompatActivity() {
    private lateinit var lblUsername: TextView
    private lateinit var lblPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)
        lblUsername = findViewById(R.id.lblUsername)
        lblPassword = findViewById(R.id.lblPassword)

        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")


        lblUsername.text = "Usuario: $username"
        lblPassword.text = "Contraseña: $password"


    }
}