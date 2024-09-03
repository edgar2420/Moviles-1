package com.example.practicalistapersonas.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicalistapersonas.R
import com.example.practicalistapersonas.models.Persona
import com.example.practicalistapersonas.ui.adapters.PersonaAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), PersonaAdapter.PersonaEventListener {
    private lateinit var lstPersonas: RecyclerView
    private lateinit var fabCreatePersona: FloatingActionButton
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val persona = result.data?.getSerializableExtra("persona") as Persona
                if(persona.id == 0) {
                    addToList(persona)
                } else {
                    updateList(persona)
                }
            }
        }

    private fun updateList(persona: Persona) {
        val adapter = lstPersonas.adapter as PersonaAdapter
        adapter.updatePersona(persona)
    }

    private fun addToList(persona: Persona) {
        val adapter = lstPersonas.adapter as PersonaAdapter
        adapter.addPersona(persona)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstPersonas = findViewById(R.id.lstPersonas)
        fabCreatePersona = findViewById(R.id.fabCreatePersona)
        setupRecyclerView()
        setupEventListeners()
    }

    private fun setupEventListeners() {
        fabCreatePersona.setOnClickListener {
            resultLauncher.launch(Intent(this, FormPersonaActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        val listaPersonas = arrayListOf(
            Persona(1, "Juan", "Perez")
        )
        val adapter = PersonaAdapter(listaPersonas, this)
        lstPersonas.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onPersonaEdit(persona: Persona) {
        val intent = Intent(this, FormPersonaActivity::class.java)
        intent.putExtra("persona", persona)
        resultLauncher.launch(intent)
    }

    override fun onPersonaDelete(persona: Persona) {
        val adapter = lstPersonas.adapter as PersonaAdapter
        adapter.deletePersona(persona)
    }
}