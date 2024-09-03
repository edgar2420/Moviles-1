package com.example.practicaactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var btnGotoActivity: Button
    private lateinit var btnSendInfo: Button
    private lateinit var txtUsername: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGotoActivity = findViewById(R.id.btnGotoActivity)
        btnSendInfo = findViewById(R.id.btnSendInfo)
        txtUsername = findViewById(R.id.txtUsername)
        setupListeners()
    }

    private fun setupListeners() {
        btnGotoActivity.setOnClickListener{
            val intent = Intent(this,SegundoActivity::class.java)
            startActivity(intent)
        }
        btnSendInfo.setOnClickListener {
            val intent = Intent (this, SegundoActivity::class.java)
            intent.putExtra("username",txtUsername.text.toString())
            startActivity(intent)
        }
    }
}