package com.example.tareapersonaadapter.objects

class Persona (
    var nombre : String,
    var apellido : String,
    var ciudad : String,
    var genero : Int
    ) {
    override fun toString(): String {
        return nombre + " " + apellido
    }
}