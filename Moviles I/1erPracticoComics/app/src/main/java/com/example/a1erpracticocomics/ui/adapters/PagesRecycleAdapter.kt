package com.example.a1erpracticocomics.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.a1erpracticocomics.R

class PagesRecycleAdapter (val data: ArrayList<Int>) :
    RecyclerView.Adapter<PagesRecycleAdapter.imagenViewHolder>() {
    class imagenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView

        init {
            img = itemView.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): imagenViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.comic_images_layout, parent, false)
        return imagenViewHolder(view)

    }

    override fun onBindViewHolder(holder: imagenViewHolder, position: Int) {
        val imagen = data[position]
        holder.img.setImageResource(imagen)

    }

    override fun getItemCount(): Int {
        return data.size
    }


}
