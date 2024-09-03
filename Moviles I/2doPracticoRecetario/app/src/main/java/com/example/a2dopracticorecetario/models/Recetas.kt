package com.example.a2dopracticorecetario.models

class Recetas(
    var nombre: String,
    var ingredientes: ArrayList<String>,
    var preparacion: String
) {
    override fun toString(): String {
        return "Recetas(nombre='$nombre', ingredientes='$ingredientes', preparacion='$preparacion)"
    }
}

