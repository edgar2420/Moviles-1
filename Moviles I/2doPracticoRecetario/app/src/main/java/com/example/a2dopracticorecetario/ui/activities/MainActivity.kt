package com.example.a2dopracticorecetario.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a2dopracticorecetario.R
import com.example.a2dopracticorecetario.models.Ingredientes
import com.example.a2dopracticorecetario.ui.adapters.ListaFiltradaAdapter
import com.example.a2dopracticorecetario.ui.adapters.MainRecycleAdapter
import kotlinx.parcelize.Parcelize

@Parcelize
class MainActivity : AppCompatActivity(), MainRecycleAdapter.OnclickListener, Parcelable {
    private lateinit var lstingredientes: RecyclerView
    private lateinit var MainRecyclerAdapter: MainRecycleAdapter
    private lateinit var ingredientesList: ArrayList<Ingredientes>
    private lateinit var btnBuscar: Button
    private var ingedientesSeleccionados: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstingredientes = findViewById(R.id.lstIngredientes)
        btnBuscar = findViewById(R.id.btnBuscar)
        ingredientesList = ArrayList()
        setupListView()
        setupListeners()
    }


    private fun setupListView() {
        val layoutManager = GridLayoutManager(this, 2)

        lstingredientes.layoutManager = layoutManager
        MainRecyclerAdapter = MainRecycleAdapter(ingredientesList, this)
        lstingredientes.adapter = MainRecyclerAdapter

        ingredientesList.add(Ingredientes("Carne", R.drawable.carne, false))
        ingredientesList.add(Ingredientes("Pollo", R.drawable.pollo, false))
        ingredientesList.add(Ingredientes("Pescado", R.drawable.pez, false))
        ingredientesList.add(Ingredientes("Lechuga", R.drawable.lechuga, false))
        ingredientesList.add(Ingredientes("Tomate", R.drawable.tomate, false))
        ingredientesList.add(Ingredientes("Papa", R.drawable.patata, false))
        ingredientesList.add(Ingredientes("Arroz", R.drawable.arroz, false))
        ingredientesList.add(Ingredientes("Cebolla", R.drawable.cebolla, false))

        MainRecyclerAdapter.notifyDataSetChanged()
    }

    override fun onClick(ingredientes: Ingredientes) {

        if (!ingredientes.selected) {
            ingedientesSeleccionados.add(ingredientes.nombre)
        } else {
            ingedientesSeleccionados.remove(ingredientes.nombre)
        }

    }

    private fun setupListeners() {
        // mostrar ingredientes seleccionados
        btnBuscar.setOnClickListener {
            if (ingedientesSeleccionados.size > 0) {
                val intent = Intent(this, ListaFiltradaActivity::class.java)
                intent.putExtra("ingredientes", ingedientesSeleccionados)

                Toast.makeText(this, ingedientesSeleccionados.toString(), Toast.LENGTH_SHORT).show()

                startActivity(intent)
            } else {
                Toast.makeText(this, "Debe seleccionar al menos un ingrediente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


