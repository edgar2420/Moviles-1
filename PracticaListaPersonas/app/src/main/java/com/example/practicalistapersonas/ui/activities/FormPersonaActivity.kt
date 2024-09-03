package com.example.practicalistapersonas.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.practicalistapersonas.R
import com.example.practicalistapersonas.models.Persona


class FormPersonaActivity : AppCompatActivity(){
    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var btnSave: Button
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_persona)

        txtName = findViewById(R.id.txtName)
        txtLastName = findViewById(R.id.txtLastName)
        btnSave = findViewById(R.id.btnSave)
        setupEventListeners()

        val persona = intent.getSerializableExtra("persona") as Persona?
        if (persona != null) {
            loadPersona(persona)
        }
    }

    private fun loadPersona(persona: Persona) {
        this.txtName.setText(persona.nombre)
        this.txtLastName.setText(persona.apellido)
        this.id = persona.id
    }

    private fun setupEventListeners() {
        btnSave.setOnClickListener { doSave() }
    }


    private fun doSave() {
        val persona = Persona(
            id,
            txtName.text.toString(),
            txtLastName.text.toString()
        )
        intent.putExtra("persona", persona)
        setResult(RESULT_OK, intent)
        finish()
    }

}