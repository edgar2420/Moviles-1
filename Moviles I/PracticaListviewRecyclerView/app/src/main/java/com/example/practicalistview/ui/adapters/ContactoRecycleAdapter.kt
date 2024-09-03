package com.example.practicalistview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicalistview.R
import com.example.practicalistview.models.Contacto

class ContactoRecycleAdapter(val data: ArrayList<Contacto>, val listener: OnContactClickListener) :
    RecyclerView.Adapter<ContactoRecycleAdapter.ContactoViewholder>() {

    class ContactoViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblTel: TextView
        val lblName: TextView
        val btnEdit: Button
        val btnDelete: Button
        init {
            lblTel = itemView.findViewById(R.id.lblTel)
            lblName = itemView.findViewById(R.id.lblName)
            btnEdit = itemView.findViewById(R.id.btnEdit)
            btnDelete = itemView.findViewById(R.id.btnBorrar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contacto_item_layout, parent, false)
        return ContactoViewholder(view)
    }

    override fun onBindViewHolder(holder: ContactoViewholder, position: Int) {
        val contacto = data[position]
        holder.lblTel.text = contacto.telefono
        holder.lblName.text = "${contacto.nombres} ${contacto.apellidos}"
        holder.btnDelete.setOnClickListener {
            listener.onDeleteClick(contacto)
        }

        holder.btnEdit.setOnClickListener {
            listener.onEditClick(contacto)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateItem(obj: Contacto){
        val index = data.indexOfFirst { it.id == obj.id }
        if (index != -1){
            data[index] = obj
            notifyItemChanged(index)
        }
    }

    fun removeItem(obj: Contacto) {
        val index = data.indexOfFirst { it.id == obj.id }
        if (index != -1) {
            data.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    interface OnContactClickListener {
        fun onEditClick(contacto: Contacto)
        fun onDeleteClick(contacto: Contacto)
    }
}