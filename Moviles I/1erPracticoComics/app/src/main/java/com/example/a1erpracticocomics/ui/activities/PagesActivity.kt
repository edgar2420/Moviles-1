package com.example.a1erpracticocomics.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.a1erpracticocomics.R
import com.example.a1erpracticocomics.ui.adapters.PagesRecycleAdapter

class PagesActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        viewPager2 = findViewById(R.id.ViewPager)

        setupListView()
    }

    private fun setupListView() {
        val Images = intent.getIntegerArrayListExtra("Pages")
        val adapter = PagesRecycleAdapter(Images!!)
        viewPager2.adapter = adapter

    }
}