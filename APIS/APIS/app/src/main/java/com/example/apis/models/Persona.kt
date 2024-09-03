package com.example.apis.models

class Persona(
    val id: Long,
    val nombres: String,
    val apellidos: String,
    val edad: Int,
    val ciudad: String,
    val fechaNacimiento: String,
    val createdAt: String,
    val updatedAt: String
) {
    override fun toString(): String {
        return "$nombres $apellidos"
    }
}