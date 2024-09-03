package com.example.a2dopracticorecetario.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a2dopracticorecetario.R
import com.example.a2dopracticorecetario.models.Recetas
import com.example.a2dopracticorecetario.ui.adapters.ListaFiltradaAdapter

class ListaFiltradaActivity : AppCompatActivity() {
    private lateinit var lstFiltrada: RecyclerView
    private lateinit var lstRecetas: ArrayList<Recetas>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_filtrada_layout)
        lstFiltrada = findViewById(R.id.lstLista)
        lstRecetas = ArrayList()
        setupListView()

    }

    private fun setupListView() {

        lstRecetas.add(Recetas("Pollo al horno", arrayListOf("Pollo","Cebolla"),"poner al horno"))
        lstRecetas.add(Recetas("Majadito", arrayListOf("Arroz","Cebolla","Carne"),"poner al horno"))
        lstRecetas.add(Recetas("Puré", arrayListOf("Papa"),"poner al horno"))
        lstRecetas.add(Recetas("Pico de gallo", arrayListOf("Tomate","Cebolla"),"poner al horno"))
        lstRecetas.add(Recetas("Filete de Pescado a la Caribeña", arrayListOf("Pescado","Cebolla","Tomate"),"poner al horno"))
        lstRecetas.add(Recetas("Bife de res", arrayListOf("Carne","Cebolla"),"poner al horno"))


        val lista = intent.getSerializableExtra("ingredientes")

        val listaFiltrada = lstRecetas.filter { receta ->
            receta.ingredientes.containsAll(lista as Collection<String>)
        }

        val adapter = ListaFiltradaAdapter(listaFiltrada)
        lstFiltrada.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        lstFiltrada.adapter = adapter



    }
}