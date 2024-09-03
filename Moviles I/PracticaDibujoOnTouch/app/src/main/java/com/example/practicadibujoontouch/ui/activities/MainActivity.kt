package com.example.practicadibujoontouch.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.practicadibujoontouch.R
import com.example.practicadibujoontouch.ui.components.MyCanvas
import com.example.practicadibujoontouch.ui.components.Shape

class MainActivity : AppCompatActivity() {
    private lateinit var btnLinea: Button
    private lateinit var btnCiirculo: Button
    private lateinit var btnRect: Button
    private lateinit var canvas: MyCanvas


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        canvas = findViewById(R.id.myCanvas)
        btnLinea = findViewById(R.id.btnLine)
        btnRect = findViewById(R.id.btnRect)
        btnCiirculo = findViewById(R.id.btnCircle)
        setupListener()

    }

    private fun setupListener() {
        btnLinea.setOnClickListener { canvas.figure = Shape.LINE }
        btnRect.setOnClickListener { canvas.figure = Shape.RECTANGLE }
        btnCiirculo.setOnClickListener { canvas.figure = Shape.CIRCLE }
    }
}



