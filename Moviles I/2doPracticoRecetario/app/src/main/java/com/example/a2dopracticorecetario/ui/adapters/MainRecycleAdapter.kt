package com.example.a2dopracticorecetario.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.a2dopracticorecetario.R
import com.example.a2dopracticorecetario.models.Ingredientes

class MainRecycleAdapter(
    private val ingredientes: List<Ingredientes>,
    private val listener: OnclickListener
) : RecyclerView.Adapter<MainRecycleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgbtn: ImageButton

        init {
            imgbtn = itemView.findViewById(R.id.imgButton)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.buttons_ingredients_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imgbtn.setImageResource(ingredientes[position].img)

        holder.imgbtn.setOnClickListener {
            listener.onClick(ingredientes[position])

            if (ingredientes.get(position).selected) {
                holder.imgbtn.setBackgroundColor(Color.parseColor("#d6d7d7"))
                ingredientes.get(position).selected = false

            } else {
                holder.imgbtn.setBackgroundColor(Color.parseColor("#4EB6CE"))
                ingredientes.get(position).selected = true
            }
        }
    }

    override fun getItemCount(): Int {
        return ingredientes.size
    }

    interface OnclickListener {
        fun onClick(ingredientes: Ingredientes)
    }
}


