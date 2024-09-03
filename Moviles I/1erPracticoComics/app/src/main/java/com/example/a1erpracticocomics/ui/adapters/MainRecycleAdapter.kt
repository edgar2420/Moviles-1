package com.example.a1erpracticocomics.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a1erpracticocomics.R
import com.example.a1erpracticocomics.models.Comic

class MainRecycleAdapter(
    val data: ArrayList<Comic>,
    val listener: OnclickListener
) : RecyclerView.Adapter<MainRecycleAdapter.ComicViewholder>() {

    class ComicViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblName: TextView
        val lblDate: TextView

        init {
            lblName = itemView.findViewById(R.id.lblName)
            lblDate = itemView.findViewById(R.id.lblDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comic_item_layout, parent, false)

        return ComicViewholder(view)
    }

    override fun onBindViewHolder(holder: ComicViewholder, position: Int) {
        val comic = data[position]

        holder.lblName.text = comic.name
        holder.lblDate.text = comic.date.toString()

        holder.itemView.setOnClickListener {
            listener.onClick(comic)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnclickListener {
        fun onClick(comic: Comic)
    }
}