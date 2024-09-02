package com.example.tareapersonaadapter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.tarea1moviles.R
import com.example.tareapersonaadapter.objects.Persona

class AdapterListaPersonas(context: Context, resource: Int, objects: MutableList<Persona>) :
    ArrayAdapter<Persona>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val rowView = inflater.inflate(R.layout.activity_lista,parent, false)

        val persona = getItem(position)

        val lbFullName = rowView.findViewById<TextView>(R.id.Name)
        val lbCity = rowView.findViewById<TextView>(R.id.City)
        val image = rowView.findViewById<ImageView>(R.id.imageView)

        lbFullName.text = persona.toString()
        lbCity.text = persona?.ciudad

        if (persona?.genero == 1){
            image.setImageResource(R.drawable.bicho)
        }else{
            image.setImageResource(R.drawable.cr7)
        }

        return rowView

    }
}