package com.example.practicalistview.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.practicalistview.R
import com.example.practicalistview.models.Persona

class PersonaAdapter(
    context: Context,
    var objects: MutableList<Persona>,

    ) :
    ArrayAdapter<Persona>(context, android.R.layout.simple_list_item_1, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {

            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.contacto_item_layout, parent, false)

            val lblName = view.findViewById<TextView>(R.id.lblName)
            val lblTel = view.findViewById<TextView>(R.id.lblTel)


            val holder = ViewHolder(lblName, lblTel)
            view.tag = holder

        }
        val holder = view?.tag as ViewHolder
        val contact = objects[position]
        holder.lblName.text = "${contact.nombres} ${contact.apellidos}"
        holder.lblTel.text = contact.ciudad
        return view
    }

    class ViewHolder(val lblName: TextView, val lblTel: TextView) {

    }
}