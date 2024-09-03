package com.example.practicaretrofit.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaretrofit.R
import com.example.practicaretrofit.models.Persona

class PersonaAdapter(
    var data: List<Persona>,
    val listener: PersonaEventListener
) :
    RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.persona_item_layout, parent, false)
        return PersonaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = data[position]
        holder.lblFullName.text = "${persona.nombres} ${persona.apellidos}"
        holder.itemView.setOnClickListener {
            listener.onPersonaClick(persona)
        }
        holder.btnDeleteItem.setOnClickListener {
            listener.onPersonaDeleteClick(persona)
        }

    }

    fun updateList(personas: List<Persona>?) {
        if (personas == null) return
        this.data = personas
        notifyDataSetChanged()
    }

    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblFullName = itemView.findViewById<TextView>(R.id.lblFullName)
        val btnDeleteItem = itemView.findViewById<ImageButton>(R.id.btnDeleteItem)
    }

    interface PersonaEventListener {
        fun onPersonaClick(persona: Persona)
        fun onPersonaDeleteClick(persona: Persona)
    }
}