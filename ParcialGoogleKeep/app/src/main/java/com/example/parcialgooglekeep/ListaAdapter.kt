package com.example.parcialgooglekeep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListaAdapter(
    private val personaList: ArrayList<Note>,
    private val personaEventListener: PersonaEventListener
) : RecyclerView.Adapter<ListaAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_add_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val persona = personaList[position]
        holder.txtTitulo.text = persona.title + " " + persona.description
        holder.btnEdit.setOnClickListener { personaEventListener.onPersonaEdit(persona) }
        holder.btnDelete.setOnClickListener { personaEventListener.onPersonaDelete(persona) }
    }

    fun addPersona(note: Note) {
        note.id = personaList.size + 1
        personaList.add(note)
        notifyItemInserted(personaList.size)

    }

    fun updatePersona(note: Note) {
        val index = personaList.indexOfFirst { it.id == note.id }
        personaList[index] = note
        notifyItemChanged(index)
    }

    fun deletePersona(note: Note) {
        val index = personaList.indexOfFirst { it.id == note.id }
        personaList.remove(note)
        notifyItemRemoved(index)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo = itemView.findViewById<TextView>(R.id.titleview)
        val txtDescripcion = itemView.findViewById<TextView>(R.id.descriptioninput)
        val btnEdit = itemView.findViewById<TextView>(R.id.btnEdit)
        val btnDelete = itemView.findViewById<TextView>(R.id.btnDelete)
    }

    interface PersonaEventListener {
        fun onPersonaEdit(note: Note)
        fun onPersonaDelete(note: Note)
    }
}