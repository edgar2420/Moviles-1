package com.example.practicalistview.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import com.example.practicalistview.R
import com.example.practicalistview.models.Persona
import com.example.practicalistview.ui.adapters.PersonaAdapter

class MainActivity : AppCompatActivity(){
    private lateinit var imgDrawable: ImageView
    private lateinit var lstNames: ListView
    private lateinit var personas: ArrayList<Persona>
    //  private lateinit var lstNames: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstNames = findViewById(R.id.lstNames)
        imgDrawable.setImageResource(R.drawable.img)
        setupListView()
        setupListeners()
    }

    private fun setupListeners() {
        lstNames.setOnItemClickListener { _, _, i, _ ->
            val contacto = personas[i]
            Toast.makeText(this, contacto.ciudad, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupListView() {
        personas = arrayListOf<Persona>(
            Persona(1, "Juan", "Perez", "Santa Cruz"),
            Persona(2, "Pedro", "Perez", "Santa Cruz"),
            Persona(3, "Carlos", "Perez", "Santa Cruz"),
            Persona(4, "Juan", "Perez", "Santa Cruz"),
            Persona(5, "Juan", "Perez", "Santa Cruz"),
            Persona(6, "Juan", "Perez", "Santa Cruz"),
            Persona(7, "Juan", "Perez", "Santa Cruz"),
            Persona(8, "Juan", "Perez", "Santa Cruz"),
            Persona(9, "Juan", "Perez", "Santa Cruz"),
            Persona(10, "Juan", "Perez", "Cochabamba"),
            Persona(11, "Juan", "Perez", "Cochabamba"),
            Persona(12, "Juan", "Perez", "Cochabamba"),
            Persona(13, "Juan", "Perez", "Cochabamba"),
            Persona(14, "Juan", "Perez", "Cochabamba"),
            Persona(15, "Juan", "Perez", "Cochabamba"),
            Persona(16, "Juan", "Perez", "Cochabamba"),
            Persona(17, "Juan", "Perez", "Cochabamba"),
            Persona(18, "Juan", "Perez", "Bogota"),
            Persona(19, "Juan", "Perez", "Bogota"),
            Persona(20, "Juan", "Perez", "Bogota"),
            Persona(21, "Juan", "Perez", "Bogota"),
            Persona(22, "Juan", "Perez", "Bogota")
        )

        /*   val adapter = ContactoRecycleAdapter(contactos)
           lstNames.layoutManager = LinearLayoutManager(
               this, LinearLayoutManager.VERTICAL,
               false
           )*/

        val adapter = PersonaAdapter(this, personas)
        lstNames.adapter = adapter
    }

}