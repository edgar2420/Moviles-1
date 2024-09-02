package com.example.practicalistapersonas.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialgooglekeep.FormPersonaActivity
import com.example.parcialgooglekeep.ListaAdapter
import com.example.parcialgooglekeep.Note
import com.example.parcialgooglekeep.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ListaAdapter.PersonaEventListener {
    private lateinit var lstPersonas: RecyclerView
    private lateinit var fabCreatePersona: FloatingActionButton
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val persona = result.data?.getSerializableExtra("title") as Note
                if(persona.id == 0) {
                    addToList(persona)
                } else {
                    updateList(persona)
                }
            }
        }

    private fun updateList(note: Note) {
        val adapter = lstPersonas.adapter as ListaAdapter
        adapter.updatePersona(note)
    }

    private fun addToList(note: Note) {
        val adapter = lstPersonas.adapter as ListaAdapter
        adapter.addPersona(note)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstPersonas = findViewById(R.id.recyclerview)
        fabCreatePersona = findViewById(R.id.btnAdd)
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
            Note(1, "Juan", "Perez"),
        )
        val adapter = ListaAdapter(listaPersonas, this)
        lstPersonas.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onPersonaEdit(note: Note) {
        val intent = Intent(this, ListaAdapter::class.java)
        intent.putExtra("title", note)
        resultLauncher.launch(intent)
    }

    override fun onPersonaDelete(note: Note) {
        val adapter = lstPersonas.adapter as ListaAdapter
        adapter.deletePersona(note)
    }
}