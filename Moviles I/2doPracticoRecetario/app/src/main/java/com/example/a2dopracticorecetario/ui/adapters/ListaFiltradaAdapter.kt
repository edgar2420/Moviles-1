package com.example.a2dopracticorecetario.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a2dopracticorecetario.R
import com.example.a2dopracticorecetario.models.Recetas

class ListaFiltradaAdapter(val data: List<Recetas>?) :
    RecyclerView.Adapter<ListaFiltradaAdapter.recetasViewHolder>() {
    class recetasViewHolder(itemView: RecyclerView) : RecyclerView.ViewHolder(itemView) {
        val txtReceta: TextView

        init {
            txtReceta = itemView.findViewById(R.id.txtReceta)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recetasViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filtrado, parent, false)
        return recetasViewHolder(RecyclerView(parent.context))
    }

    override fun onBindViewHolder(holder: recetasViewHolder, position: Int) {
        val receta = data?.get(position)
        holder.txtReceta.text = receta.toString()
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}