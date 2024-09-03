package com.example.practicarecyclerview.models

data class Persona(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val edad: Int,
    val genero: Int,
    val ciudad: String,
){
    override fun toString(): String {
        return "$nombre $apellido"
    }
}
