package com.example.practicomvvm

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonaViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
    var lblName = itemView.findViewById<TextView>(R.id.lblNombre)
}
