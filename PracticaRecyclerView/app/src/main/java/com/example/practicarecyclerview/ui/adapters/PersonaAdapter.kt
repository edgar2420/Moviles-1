package com.example.practicarecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicarecyclerview.R
import com.example.practicarecyclerview.models.Persona

class PersonaAdapter(
    private val personaList: ArrayList<Persona>,
    val personaListener: PersonaListener
) : RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.lista_item_layout, parent, false)
        return PersonaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = personaList[position]
        holder.lblFullName.text = persona.nombre + " " + persona.apellido
        holder.lblCity.text = persona.ciudad
//        holder.itemView.setOnClickListener{
//            personaListener.onPersonaClick(persona)
//        }
        holder.lblFullName.setOnClickListener {
            personaListener.onPersonaClick(persona)
        }
        holder.lblCity.setOnClickListener {
            personaListener.onPersonaClick(persona)
        }
    }

    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblFullName: TextView = itemView.findViewById(R.id.lblFullName)
        val lblCity: TextView = itemView.findViewById(R.id.lblCity)
    }

    interface PersonaListener {
        fun onPersonaClick(persona: Persona)
    }
}