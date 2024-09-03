package com.moviles.practicacomponentes.ui.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.moviles.practicacomponentes.R

class EscalaNumeros(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var value: Int = 0
    private var btnPlus: Button
    private var btnMinus: Button
    private var lbl1: TextView
    private var lbl2: TextView
    private var lbl3: TextView
    private var lbl4: TextView
    private var lbl5: TextView


    init {
        val inflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_escalanumeros, this)

        btnPlus = view.findViewById(R.id.btnPlus)
        btnMinus = view.findViewById(R.id.btnMinus)
        lbl1 = findViewById(R.id.lbl1)
        lbl2 = findViewById(R.id.lbl2)
        lbl3 = findViewById(R.id.lbl3)
        lbl4 = findViewById(R.id.lbl4)
        lbl5 = findViewById(R.id.lbl5)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnPlus.setOnClickListener { addValue() }
        btnMinus.setOnClickListener { removeValue() }
    }

    private fun removeValue() {
        if (value <= 1){
            return
        }
        value--
        updateUI()
    }

    private fun addValue() {
        if (value == 5){
            return
        }
        value++
        updateUI()
    }

    private fun updateUI() {
        val arrLabels = arrayListOf(lbl1, lbl2, lbl3, lbl4, lbl5)
        for (i in 1..5) {
            if (value == i) {
                paintSelected(arrLabels[i - 1])
            } else {
                paintDeselected(arrLabels[i - 1])
            }
        }

    }

    private fun paintDeselected(lbl: TextView) {
        lbl.setBackgroundResource(android.R.color.transparent)
       /* lbl.setBackgroundColor(Color.WHITE)
        lbl.setTextColor(Color.BLACK)*/
    }

    private fun paintSelected(lbl: TextView) {
    lbl.setBackgroundResource(R.drawable.circle)
    /*
        lbl.setBackgroundColor(Color.BLACK)
        lbl.setTextColor(Color.WHITE)*/
    }
}