package com.example.practicaactivityintents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var txtUsername: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtUsername = findViewById(R.id.txtUsername)
        txtPassword = findViewById(R.id.txtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        setupListeners()
    }

    private fun setupListeners() {
        btnLogin.setOnClickListener {
            val intent = Intent(this, DatosActivity::class.java)

            if (txtUsername.text.toString().trim().isNotBlank()) {
                if (txtPassword.text.toString().trim().isNotBlank()) {
                    intent.putExtra("username", txtUsername.text.toString())
                    intent.putExtra("password", txtPassword.text.toString())
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Debes ingresar una contraseña",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Debes ingresar un usuario",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}