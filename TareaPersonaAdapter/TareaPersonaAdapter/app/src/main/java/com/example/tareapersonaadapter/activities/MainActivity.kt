package com.example.tareapersonaadapter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.tareapersonaadapter.R
import com.example.tareapersonaadapter.adapters.AdapterListaPersonas
import com.example.tareapersonaadapter.objects.Persona

class MainActivity : AppCompatActivity() {

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        setup()
    }

    private fun setup() {
        val listaPersonas : ArrayList<Persona> = arrayListOf(
            Persona("Ale", "Hurtado", "Santa Cruz", 1),
            Persona("Julia", "Chavez", "Santa Cruz", 0),
            Persona("David", "Ribera", "Santa Cruz", 1),
            Persona("Andrea", "Rojas", "Cochabamba", 0),
            Persona("Bladimir", "Melgar", "La Paz", 1),
            Persona("Alexia", "Baptista", "Oruro", 0),
            Persona("Pedro", "Alvarez", "Potosi", 1),
            Persona("Pedro", "Alvarez", "Potosi", 1),
            Persona("Pedro", "Alvarez", "Potosi", 1),
            Persona("Pedro", "Alvarez", "Potosi", 1)
        )

        val adapter = AdapterListaPersonas(this,R.layout.activity_list_view_persona,listaPersonas)

        listView.adapter = adapter
    }
}