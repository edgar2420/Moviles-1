package com.example.practicomvvm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainViewModel : ViewModel(){
    private var _lista = MutableLiveData<ArrayList<Persona>>()
    val lista : LiveData<ArrayList<Persona>> get() = _lista

    fun setupRecycler(context: Context, recycler : RecyclerView){
        _lista.value = arrayListOf(Persona("Edgar", "Rojas"))
        recycler.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = Nombre(_lista.value!!)
        }
    }
}