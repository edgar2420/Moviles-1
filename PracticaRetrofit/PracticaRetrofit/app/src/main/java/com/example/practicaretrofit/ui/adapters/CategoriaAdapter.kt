package com.example.practicaretrofit.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaretrofit.R
import com.example.practicaretrofit.models.Categoria

class CategoriaAdapter(
    var data: List<Categoria>,
    val listener: categoriaEventListener
) :
    RecyclerView.Adapter<CategoriaAdapter.categoriaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoriaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.categoria_item_layout, parent, false)
        return categoriaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: categoriaViewHolder, position: Int) {
        val categoria = data[position]
        holder.lblFullName.text = "${categoria.nombre} ${categoria.productos}"
        holder.itemView.setOnClickListener {
            listener.oncategoriaClick(categoria)
        }
        holder.btnDeleteItem.setOnClickListener {
            listener.oncategoriaDeleteClick(categoria)
        }

    }

    fun updateList(categorias: List<Categoria>?) {
        if (categorias == null) return
        this.data = categorias
        notifyDataSetChanged()
    }

    class categoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblFullName = itemView.findViewById<TextView>(R.id.lblFullName)
        val btnDeleteItem = itemView.findViewById<ImageButton>(R.id.btnDeleteItem)
    }

    interface categoriaEventListener {
        fun oncategoriaClick(categoria: Categoria)
        fun oncategoriaDeleteClick(categoria:Categoria )
    }
}