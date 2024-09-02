package com.example.parcialgooglekeep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class FormPersonaActivity : AppCompatActivity() {
    private lateinit var txtName: EditText
    private lateinit var txtDescription: EditText
    private lateinit var btnSave: Button

    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        txtName = findViewById(R.id.titleview)
        txtDescription = findViewById(R.id.descriptioninput)
        btnSave = findViewById(R.id.btnSave)
        setupEventListeners()

        val persona = intent.getSerializableExtra("title") as Note?
        if (persona != null) {
            loadPersona(persona)
        }
    }

    private fun loadPersona(note: Note) {
        this.txtName.setText(note.title)
        this.txtDescription.setText(note.description)
        this.id = note.id
    }

    private fun setupEventListeners() {
        btnSave.setOnClickListener { doSave() }
    }


    private fun doSave() {
        val persona = Note(
            id,
            txtName.text.toString(),
            txtDescription.text.toString()
        )
        intent.putExtra("title", persona)
        setResult(RESULT_OK, intent)
        finish()
    }

}