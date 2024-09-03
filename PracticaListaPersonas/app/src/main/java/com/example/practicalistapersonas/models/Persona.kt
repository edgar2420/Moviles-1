package com.example.practicalistapersonas.models

data class Persona(
    var id: Int,
    val nombre: String,
    val apellido: String,
): java.io.Serializable{
    override fun toString(): String {
        return "$nombre $apellido"
    }
}
