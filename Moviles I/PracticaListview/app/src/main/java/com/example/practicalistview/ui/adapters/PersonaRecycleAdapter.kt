package com.example.practicalistview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicalistview.R
import com.example.practicalistview.models.Persona

class PersonaRecycleAdapter(val data: ArrayList<Persona>) :
    RecyclerView.Adapter<PersonaRecycleAdapter.ContactoViewholder>() {

    class ContactoViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val lblTel: TextView
        val lblName: TextView

        init {
            lblTel = itemView.findViewById(R.id.lblTel)
            lblName = itemView.findViewById(R.id.lblName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contacto_item_layout, parent, false)
        return ContactoViewholder(view)
    }

    override fun onBindViewHolder(holder: ContactoViewholder, position: Int) {
    val contacto = data[position]
        holder.lblTel.text = contacto.ciudad
        holder.lblName.text = "${contacto.nombres} ${contacto.apellidos}"

    }

    override fun getItemCount(): Int {
        return data.size
    }
}