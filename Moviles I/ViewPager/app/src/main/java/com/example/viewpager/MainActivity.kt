package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: MySimpleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        mAdapter = MySimpleAdapter(dummyData())
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = mAdapter
    }

    private fun dummyData() = listOf(
        "dog",
        "cat",
        "owl",
        "cheetah",
        "raccoon",
        "bird",
        "snake",
        "lizard",
        "hamster",
        "bear",
        "lion",
        "tiger",
        "horse",
        "frog",
        "fish",
        "shark",
        "turtle",
        "elephant",
        "cow",
        "beaver",
        "bison",
        "porcupine",
        "rat",
        "mouse",
        "goose",
        "deer",
        "fox",
        "moose",
        "buffalo",
        "monkey",
        "penguin",
        "parrot",
        "chocobo"
    )


}