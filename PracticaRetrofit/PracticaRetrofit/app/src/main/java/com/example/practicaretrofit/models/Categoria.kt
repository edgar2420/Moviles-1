package com.example.practicaretrofit.models

data class Categoria (
    val id:Int,
    val nombre: String,
    val productos: MutableList<Producto> = mutableListOf()



)