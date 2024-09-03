package com.example.a2dopracticorecetario.models

class Ingredientes(
    var nombre: String,
    var img: Int,
    var selected: Boolean
) {
    override fun toString(): String {
        return "Ingredientes(nombre='$nombre', img=$img)"
    }

}