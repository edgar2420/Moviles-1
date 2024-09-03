package com.example.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MySimpleAdapter(private val mDataSet: List<String>?) :
    RecyclerView.Adapter<MySimpleAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_page, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = mDataSet?.get(position)
        data?.let { holder.bindItems(it) }
    }

    override fun getItemCount(): Int {
        return mDataSet?.size ?: 0
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val text1 = v.findViewById(android.R.id.text1) as TextView

        fun bindItems(data: String) {
            text1.text = data
        }

    }

}