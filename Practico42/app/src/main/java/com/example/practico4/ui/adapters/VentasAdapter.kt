package com.example.practicaretrofit.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaretrofit.R
import com.example.practicaretrofit.models.Ventas

class VentasAdapter(
    var data: List<Ventas>,
    val listener: VentasEventListener
) :
    RecyclerView.Adapter<VentasAdapter.ventasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ventasViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ventas_item_layout, parent, false)
        return ventasViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ventasViewHolder, position: Int) {
        val ventas = data[position]
        holder.lblFullName.text = "${ventas.nombre} "
        holder.itemView.setOnClickListener {
            listener.onventasClick(ventas)
        }
        holder.btnDeleteItem.setOnClickListener {
            listener.onventasDeleteClick(ventas)
        }

    }

    fun updateList(ventas: List<Ventas>?) {
        if (ventas == null) return
        this.data = ventas
        notifyDataSetChanged()
    }

    class ventasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblFullName = itemView.findViewById<TextView>(R.id.lblFullName)
        val btnDeleteItem = itemView.findViewById<ImageButton>(R.id.btnDeleteItem)
    }

    interface VentasEventListener {
        fun onventasClick(ventas: Ventas)
        fun onventasDeleteClick(ventas: Ventas )
    }
}