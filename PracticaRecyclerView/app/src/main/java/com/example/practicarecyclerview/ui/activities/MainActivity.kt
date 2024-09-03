package com.example.practicarecyclerview.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicarecyclerview.R
import com.example.practicarecyclerview.models.Persona
import com.example.practicarecyclerview.ui.adapters.PersonaAdapter

class MainActivity : AppCompatActivity(), PersonaAdapter.PersonaListener {
    private lateinit var lstPersonas: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstPersonas = findViewById(R.id.lstPersonas)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val listaPersonas: ArrayList<Persona> = arrayListOf(
            Persona(1, "Juan", "Perez", 20, 1, "Bogota"),
            Persona(2, "Pedro", "Perez", 20, 1, "Bogota"),
            Persona(3, "Maria", "Perez", 20, 2, "Santa Cruz"),
            Persona(4, "Jose", "Perez", 20, 1, "La Paz"),
            Persona(5, "Luis", "Perez", 20, 1, "Cochabamba"),
            Persona(6, "Ana", "Perez", 20, 2, "Bogota"),
            Persona(7, "Luisa", "Perez", 20, 2, "Bogota"),
            Persona(8, "Carlos", "Perez", 20, 1, "Bogota"),
            Persona(9, "Rosa", "Perez", 20, 2, "Bogota"),
            Persona(10, "Ricardo", "Perez", 20, 1, "Bogota"),
            Persona(11, "Miguel", "Perez", 20, 1, "Bogota"),
            Persona(12, "Fernando", "Perez", 20, 1, "Bogota"),
            Persona(1, "Juan", "Perez", 20, 1, "Bogota"),
            Persona(2, "Pedro", "Perez", 20, 1, "Bogota"),
            Persona(3, "Maria", "Perez", 20, 2, "Santa Cruz"),
            Persona(4, "Jose", "Perez", 20, 1, "La Paz"),
            Persona(5, "Luis", "Perez", 20, 1, "Cochabamba"),
            Persona(6, "Ana", "Perez", 20, 2, "Bogota"),
            Persona(7, "Luisa", "Perez", 20, 2, "Bogota"),
            Persona(8, "Carlos", "Perez", 20, 1, "Bogota"),
            Persona(9, "Rosa", "Perez", 20, 2, "Bogota"),
            Persona(10, "Ricardo", "Perez", 20, 1, "Bogota"),
            Persona(11, "Miguel", "Perez", 20, 1, "Bogota"),
            Persona(12, "Fernando", "Perez", 20, 1, "Bogota"),
            Persona(1, "Juan", "Perez", 20, 1, "Bogota"),
            Persona(2, "Pedro", "Perez", 20, 1, "Bogota"),
            Persona(3, "Maria", "Perez", 20, 2, "Santa Cruz"),
            Persona(4, "Jose", "Perez", 20, 1, "La Paz"),
            Persona(5, "Luis", "Perez", 20, 1, "Cochabamba"),
            Persona(6, "Ana", "Perez", 20, 2, "Bogota"),
            Persona(7, "Luisa", "Perez", 20, 2, "Bogota"),
            Persona(8, "Carlos", "Perez", 20, 1, "Bogota"),
            Persona(9, "Rosa", "Perez", 20, 2, "Bogota"),
            Persona(10, "Ricardo", "Perez", 20, 1, "Bogota"),
            Persona(11, "Miguel", "Perez", 20, 1, "Bogota"),
            Persona(12, "Fernando", "Perez", 20, 1, "Bogota"),
            Persona(1, "Juan", "Perez", 20, 1, "Bogota"),
            Persona(2, "Pedro", "Perez", 20, 1, "Bogota"),
            Persona(3, "Maria", "Perez", 20, 2, "Santa Cruz"),
            Persona(4, "Jose", "Perez", 20, 1, "La Paz"),
            Persona(5, "Luis", "Perez", 20, 1, "Cochabamba"),
            Persona(6, "Ana", "Perez", 20, 2, "Bogota"),
            Persona(7, "Luisa", "Perez", 20, 2, "Bogota"),
            Persona(8, "Carlos", "Perez", 20, 1, "Bogota"),
            Persona(9, "Rosa", "Perez", 20, 2, "Bogota"),
            Persona(10, "Ricardo", "Perez", 20, 1, "Bogota"),
            Persona(11, "Miguel", "Perez", 20, 1, "Bogota"),
            Persona(12, "Fernando", "Perez", 20, 1, "Bogota"),
            Persona(1, "Juan", "Perez", 20, 1, "Bogota"),
            Persona(2, "Pedro", "Perez", 20, 1, "Bogota"),
            Persona(3, "Maria", "Perez", 20, 2, "Santa Cruz"),
            Persona(4, "Jose", "Perez", 20, 1, "La Paz"),
            Persona(5, "Luis", "Perez", 20, 1, "Cochabamba"),
            Persona(6, "Ana", "Perez", 20, 2, "Bogota"),
            Persona(7, "Luisa", "Perez", 20, 2, "Bogota"),
            Persona(8, "Carlos", "Perez", 20, 1, "Bogota"),
            Persona(9, "Rosa", "Perez", 20, 2, "Bogota"),
            Persona(10, "Ricardo", "Perez", 20, 1, "Bogota"),
            Persona(11, "Miguel", "Perez", 20, 1, "Bogota"),
            Persona(12, "Fernando", "Perez", 20, 1, "Bogota"),
            Persona(1, "Juan", "Perez", 20, 1, "Bogota"),
            Persona(2, "Pedro", "Perez", 20, 1, "Bogota"),
            Persona(3, "Maria", "Perez", 20, 2, "Santa Cruz"),
            Persona(4, "Jose", "Perez", 20, 1, "La Paz"),
            Persona(5, "Luis", "Perez", 20, 1, "Cochabamba"),
            Persona(6, "Ana", "Perez", 20, 2, "Bogota"),
            Persona(7, "Luisa", "Perez", 20, 2, "Bogota"),
            Persona(8, "Carlos", "Perez", 20, 1, "Bogota"),
            Persona(9, "Rosa", "Perez", 20, 2, "Bogota"),
            Persona(10, "Ricardo", "Perez", 20, 1, "Bogota"),
            Persona(11, "Miguel", "Perez", 20, 1, "Bogota"),
            Persona(12, "Fernando", "Perez", 20, 1, "Bogota"),
            Persona(1, "Juan", "Perez", 20, 1, "Bogota"),
            Persona(2, "Pedro", "Perez", 20, 1, "Bogota"),
            Persona(3, "Maria", "Perez", 20, 2, "Santa Cruz"),
            Persona(4, "Jose", "Perez", 20, 1, "La Paz"),
            Persona(5, "Luis", "Perez", 20, 1, "Cochabamba"),
            Persona(6, "Ana", "Perez", 20, 2, "Bogota"),
            Persona(7, "Luisa", "Perez", 20, 2, "Bogota"),
            Persona(8, "Carlos", "Perez", 20, 1, "Bogota"),
            Persona(9, "Rosa", "Perez", 20, 2, "Bogota"),
            Persona(10, "Ricardo", "Perez", 20, 1, "Bogota"),
            Persona(11, "Miguel", "Perez", 20, 1, "Bogota"),
            Persona(12, "Fernando", "Perez", 20, 1, "Bogota"),
        )

        val lstAdapter = PersonaAdapter(listaPersonas, this)
        lstPersonas.apply {
            layoutManager = LinearLayoutManager(this@MainActivity) //vertical
//            layoutManager = LinearLayoutManager(this@MainActivity).apply { //horizontal
//                orientation = LinearLayoutManager.HORIZONTAL
//            }
            adapter = lstAdapter
        }
    }

    override fun onPersonaClick(persona: Persona) {
        Toast.makeText(this, persona.toString(), Toast.LENGTH_SHORT).show()
    }
}