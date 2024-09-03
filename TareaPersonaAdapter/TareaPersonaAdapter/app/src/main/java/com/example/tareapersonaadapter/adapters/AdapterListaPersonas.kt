package com.example.tareapersonaadapter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.tareapersonaadapter.R
import com.example.tareapersonaadapter.objects.Persona

class AdapterListaPersonas(context: Context, resource: Int, objects: MutableList<Persona>) :
    ArrayAdapter<Persona>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val rowView = inflater.inflate(R.layout.activity_list_view_persona,parent, false)

        val persona = getItem(position)

        val lbFullName = rowView.findViewById<TextView>(R.id.name)
        val lbCity = rowView.findViewById<TextView>(R.id.city)
        val image = rowView.findViewById<ImageView>(R.id.img_persona)

        lbFullName.text = persona.toString()
        lbCity.text = persona?.ciudad

        if (persona?.genero == 1){
            image.setImageResource(R.drawable.hombre)
        }else{
            image.setImageResource(R.drawable.mujer)
        }

        return rowView

    }
}