package com.example.practico2lista.models

data class Persona(
    val nombre: String
): java.io.Serializable{
    override fun toString(): String {
        return "$nombre"
    }
}
