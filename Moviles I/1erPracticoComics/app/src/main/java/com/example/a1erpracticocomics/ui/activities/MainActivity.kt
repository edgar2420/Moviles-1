package com.example.a1erpracticocomics.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a1erpracticocomics.R
import com.example.a1erpracticocomics.models.Comic
import com.example.a1erpracticocomics.ui.adapters.MainRecycleAdapter


class MainActivity : AppCompatActivity(), MainRecycleAdapter.OnclickListener {
    private lateinit var lstComics: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstComics = findViewById(R.id.lstComics)
        setupListView()
    }

    private fun setupListView() {
        val spiderman = arrayOf<Int>(
            R.drawable.as01,
            R.drawable.as02,
            R.drawable.as03,
            R.drawable.as04,
            R.drawable.as05,
            R.drawable.as06,
            R.drawable.as07,
            R.drawable.as08,
            R.drawable.as09,
            R.drawable.as10
        )

        val ironman = arrayOf<Int>(
            R.drawable.im01,
            R.drawable.im02,
            R.drawable.im03,
            R.drawable.im04,
            R.drawable.im05,
            R.drawable.im06,
            R.drawable.im07,
            R.drawable.im08,
            R.drawable.im09,
            R.drawable.im10,
        )

        val superiorironman = arrayOf<Int>(
            R.drawable.sim01,
            R.drawable.sim02,
            R.drawable.sim03,
            R.drawable.sim04,
            R.drawable.sim05,
            R.drawable.sim06,
            R.drawable.sim07,
            R.drawable.sim08,
            R.drawable.sim09,
            R.drawable.sim10,
        )

        val comics = arrayListOf<Comic>(
            Comic("The Amazing Spiderman", 1960, spiderman),
            Comic("Iron Man", 1968, ironman),
            Comic("Superior Iron Man", 2015, superiorironman)
        )

        val adapter = MainRecycleAdapter(comics, this)
        lstComics.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL,
            false
        )

        lstComics.adapter = adapter
    }

    override fun onClick(comic: Comic) {
        val intent = Intent(this, PagesActivity::class.java)
        var lstImages: ArrayList<Int> = ArrayList()
        for (i in 0..comic.img.size - 1) {
            lstImages.add(comic.img[i])
        }
        intent.putExtra("Pages", lstImages)
        startActivity(intent)
    }
}