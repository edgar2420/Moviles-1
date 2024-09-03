package com.example.practicacalculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.practicacalculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private var result: String = ""
    private var operation: OperationType = OperationType.NONE
    private var aux: Int = 0

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupListeners()
    }

    private fun setupListeners() {

        binding.btnUno.setOnClickListener { addNumber("1") }
        binding.btnDos.setOnClickListener { addNumber("2") }
        binding.btnTres.setOnClickListener { addNumber("3") }
        binding.btnCuatro.setOnClickListener { addNumber("4") }
        binding.btnCinco.setOnClickListener { addNumber("5") }
        binding.btnSeis.setOnClickListener { addNumber("6") }
        binding.btnSiete.setOnClickListener { addNumber("7") }
        binding.btnOcho.setOnClickListener { addNumber("8") }
        binding.btnNueve.setOnClickListener { addNumber("9") }
        binding.btnCero.setOnClickListener { addNumber("0") }
        binding.btnBorrar.setOnClickListener { clearOne() }
        binding.btnBorrarTodo.setOnClickListener { clearEverything() }

        binding.btnSumar.setOnClickListener { startOperation(OperationType.ADD) }
        binding.btnRestar.setOnClickListener { startOperation(OperationType.SUBSTRACT) }
        binding.btnMultiplicar.setOnClickListener { startOperation(OperationType.MULTIPLY) }
        binding.btnDividir.setOnClickListener { startOperation(OperationType.DIVIDE) }
        binding.btnIgual.setOnClickListener { solveOperation() }
    }

    private fun solveOperation() {
        val secondNumber: Int = result.toInt()
        var operationResult = 0
        when (operation) {
            OperationType.ADD -> operationResult = aux + secondNumber
            OperationType.SUBSTRACT -> operationResult = aux - secondNumber
            OperationType.MULTIPLY -> operationResult = aux * secondNumber
            OperationType.DIVIDE -> operationResult = aux / secondNumber
            OperationType.NONE -> {}
        }
        result = operationResult.toString()
        updateResult()
    }


    private fun startOperation(type: OperationType) {
        operation = type
        aux = result.toInt()
        result = ""
        updateResult()

    }

    private fun clearEverything() {
        result = ""
        updateResult()
    }

    private fun updateResult() {
        if (result.isEmpty()) {
            binding.lblResultado.text = "0"
        } else {
            binding.lblResultado.text = result
        }
    }

    private fun clearOne() {
        if (result.isEmpty()) {
            return
        }
        result = result.substring(0, result.length - 1)
        updateResult()
    }

    private fun addNumber(num: String) {
        if (num == "0" && result == "") {
            return
        }
        result += num
        updateResult()
    }
}