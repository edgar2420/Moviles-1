package com.example.practicaretrofit.models


data class Producto(
    val id:Int,
    val nombre: String,
    val descripcion: String,
    val precio_actual: Double,
    val categoria_id: Int,
    val categoria: Categoria
)