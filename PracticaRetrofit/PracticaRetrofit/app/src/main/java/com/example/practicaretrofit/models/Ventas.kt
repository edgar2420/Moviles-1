package com.example.practicaretrofit.models

class Ventas (
        val id: Int,
        val nombre : String,
        val nit: Int,
        val usuario: String,
        val detalle: MutableList<CantidadPrecio> = mutableListOf()


        )
