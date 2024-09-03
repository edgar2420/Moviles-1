package com.example.practicalayouts

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btn0: Button
    private lateinit var btnPlus: Button
    private lateinit var btnMinus: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button
    private lateinit var btnClearOne: Button
    private lateinit var btnClearEverything: Button
    private lateinit var btnEquals: Button
    private lateinit var lblResult: TextView
    private var result = ""
    private var aux = 0
    private var currentOperation = Operation.NINGUNA
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btn0 = findViewById(R.id.btn0)
        btnPlus = findViewById(R.id.btnPlus)
        btnMinus = findViewById(R.id.btnMinus)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)
        btnClearOne = findViewById(R.id.btnClearOne)
        btnClearEverything = findViewById(R.id.btnClearEverything)
        btnEquals = findViewById(R.id.btnEquals)
        lblResult = findViewById(R.id.lblResult)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btn0.setOnClickListener { addNumber(0) }
        btn1.setOnClickListener { addNumber(1) }
        btn2.setOnClickListener { addNumber(2) }
        btn3.setOnClickListener { addNumber(3) }
        btn4.setOnClickListener { addNumber(4) }
        btn5.setOnClickListener { addNumber(5) }
        btn6.setOnClickListener { addNumber(6) }
        btn7.setOnClickListener { addNumber(7) }
        btn8.setOnClickListener { addNumber(8) }
        btn9.setOnClickListener { addNumber(9) }
        btnClearOne.setOnClickListener { clearOne() }
        btnClearEverything.setOnClickListener { clearEverything() }
        btnPlus.setOnClickListener { startOperation(Operation.SUMA) }
        btnMinus.setOnClickListener { startOperation(Operation.RESTA) }
        btnMultiply.setOnClickListener { startOperation(Operation.MULTIPLICACION) }
        btnDivide.setOnClickListener { startOperation(Operation.DIVISION) }
        btnEquals.setOnClickListener { closeOperation() }
    }

    private fun closeOperation() {
        val numB = result.toInt()
        when (currentOperation) {
            Operation.SUMA -> {
                result = (aux + numB).toString()
            }
            Operation.RESTA -> {
                result = (aux - numB).toString()
            }
            Operation.MULTIPLICACION -> {
                result = (aux * numB).toString()
            }
            Operation.DIVISION -> {
                result = (aux / numB).toString()
            }
            else -> {
                result = ""}
        }
        reloadScreen()
    }

    private fun startOperation(operation: Operation) {
        aux = result.toInt()
        currentOperation = operation
        result = ""
        reloadScreen()
    }

    private fun clearEverything() {
        result = ""
        reloadScreen()
    }

    private fun clearOne() {
        if (result.isEmpty()) {
            return
        }
        result = result.substring(0, result.length - 1)
        reloadScreen()
    }

    private fun addNumber(num: Int) {
        result += num.toString()
        reloadScreen()
    }

    private fun reloadScreen() {
        if (result.isEmpty())
            lblResult.text = "0"
        else
            lblResult.text = result
    }
}