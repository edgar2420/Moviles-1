package com.example.practicaimagenes

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var imgInternet: ImageView
    private lateinit var imgDrawable: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgInternet = findViewById(R.id.imgInternet)
        imgDrawable = findViewById(R.id.imgDrawable)

        imgDrawable.setImageResource(R.drawable.test)
        val imagen = "https://kinsta.com/es/wp-content/uploads/sites/8/2020/10/tipos-de-archivos-de-imagen.png"


        Glide.with(this)
            .load(imagen)
            .into(imgInternet)

    }
}