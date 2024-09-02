package com.example.practicaretrofit.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaretrofit.R
import com.example.practicaretrofit.models.Producto

class ProductoAdapter(
    var data: List<Producto>,
    val listener: ProductoEventListener
) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.producto_item_layout, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = data[position]

        holder.lblFullName.text = "${producto.nombre}"
        holder.lblDescripcion.text = "${producto.descripcion}"
        holder.itemView.setOnClickListener {
            listener.onProductoClick(producto)
        }
        holder.btnDeleteItem.setOnClickListener {
            listener.onProductoDeleteClick(producto)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateList(producto: List<Producto>?) {
        if (producto == null) return
        this.data = producto
        notifyDataSetChanged()
    }

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblFullName = itemView.findViewById<TextView>(R.id.lblFullName)
        val btnDeleteItem = itemView.findViewById<ImageButton>(R.id.btnDeleteItem)
        val lblDescripcion = itemView.findViewById<TextView>(R.id.lblDescripcion)
    }

    interface ProductoEventListener {
        fun onProductoClick(producto:Producto)
        fun onProductoDeleteClick(producto:Producto)
    }
}