package com.example.practico2lista.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practico2lista.R
import com.example.practico2lista.models.Persona

class PersonaAdapter(
    private val personaList: ArrayList<Persona>,
    private val personaEventListener: PersonaEventListener
) : RecyclerView.Adapter<PersonaAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.lista_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val persona = personaList[position]
        holder.lblFullName.text = persona.nombre
        holder.btnEdit.setOnClickListener { personaEventListener.onPersonaEdit(persona) }
        holder.btnDelete.setOnClickListener { personaEventListener.onPersonaDelete(persona) }
    }

    fun addPersona(persona: Persona) {
        persona.id = personaList.size + 1
        personaList.add(persona)
        notifyItemInserted(personaList.size)

    }

    fun updatePersona(persona: Persona) {
        val index = personaList.indexOfFirst { it.id == persona.id }
        personaList[index] = persona
        notifyItemChanged(index)
    }

    fun deletePersona(persona: Persona) {
        val index = personaList.indexOfFirst { it.id == persona.id }
        personaList.remove(persona)
        notifyItemRemoved(index)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblFullName = itemView.findViewById<TextView>(R.id.lblFullName)
        val lblPhone = itemView.findViewById<TextView>(R.id.lblPhone)
        val btnEdit = itemView.findViewById<TextView>(R.id.btnEdit)
        val btnDelete = itemView.findViewById<TextView>(R.id.btnDelete)
    }

    interface PersonaEventListener {
        fun onPersonaEdit(persona: Persona)
        fun onPersonaDelete(persona: Persona)
    }
}