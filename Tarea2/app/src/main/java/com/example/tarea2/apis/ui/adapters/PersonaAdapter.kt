package com.example.apis.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apis.models.Persona
import com.example.tarea2.R

class PersonaAdapter(
    var personas: List<Persona>,
    var listener : OnPersonaClickListener
) : RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.persona_layout, parent, false)
        return PersonaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personas.size
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = personas[position]
        holder.lbNombreCompleto.text = persona.toString()
        holder.lbNombreCompleto.setOnClickListener{
            listener.onPersonaClick(persona)
        }
        holder.btnDelete.setOnClickListener{
            listener.onPersonaDelete(persona)
        }
    }

    fun updateList(newPersonas: List<Persona>) {
        if (newPersonas.isEmpty()) return
        this.personas = newPersonas
        notifyDataSetChanged()
    }

    interface OnPersonaClickListener {
        fun onPersonaClick(persona: Persona)
        fun onPersonaDelete(persona: Persona)
    }

    class PersonaViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val lbNombreCompleto = itemView.findViewById<TextView>(R.id.nombreCompleto)
        val btnDelete = itemView.findViewById<ImageButton>(R.id.btnDelete)
    }

}