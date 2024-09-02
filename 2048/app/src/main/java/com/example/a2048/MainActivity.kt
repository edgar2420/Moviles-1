package com.example.a2048

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.androidgamesdk.GameActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStart.setOnClickListener {
            val newIntent = Intent(this, GameActivity::class.java)
            startActivity(newIntent)
        }

        buttonExit.setOnClickListener {
            finish()
        }
    }
}
