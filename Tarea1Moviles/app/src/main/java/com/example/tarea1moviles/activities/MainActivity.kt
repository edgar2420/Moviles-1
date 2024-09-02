package com.example.tareapersonaadapter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.tarea1moviles.R
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
            Persona("Edgar","Rojas", "Santa Cruz", 1),
            Persona("Ada","Salvatierra", "Santa Cruz", 0),
            Persona("Marcelo","Arce", "Santa Cruz", 1),

        )

        val adapter = AdapterListaPersonas(this,R.layout.activity_lista,listaPersonas)

        listView.adapter = adapter
    }
}