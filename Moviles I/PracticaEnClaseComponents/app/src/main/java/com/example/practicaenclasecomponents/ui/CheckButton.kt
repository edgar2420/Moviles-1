package com.example.practicaenclasecomponents.ui

import android.R.attr.button
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.practicaenclasecomponents.R


class CheckButton(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private lateinit var btnSi: ImageButton
    private lateinit var btnNo: ImageButton


    init {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_check, this)

        btnSi = findViewById(R.id.btnSi)
        btnNo = findViewById(R.id.btnNo)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnSi.setOnClickListener { paintLblSi() }
        btnNo.setOnClickListener { paintLblNo() }
    }

    private fun paintLblNo() {
        btnNo.setBackgroundTintList(ContextCompat.getColorStateList(context,
            android.R.color.holo_red_dark
        ));

        btnSi.setBackgroundTintList(ContextCompat.getColorStateList(context,
            android.R.color.holo_blue_light
        ));


        //   btnNo.backgroundTintList(android.R.color.holo_green_light)
     //   btnSi.setBackgroundResource(android.R.color.transparent)
    }

    private fun paintLblSi() {
        btnSi.setBackgroundTintList(ContextCompat.getColorStateList(context,
            android.R.color.holo_green_light
        ));

        btnNo.setBackgroundTintList(ContextCompat.getColorStateList(context,
            android.R.color.holo_blue_light
        ));
    }


}