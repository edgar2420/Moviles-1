package com.example.practicalistview.models

class Persona (
    var id:Int,
    var nombres:String,
    var apellidos:String,
    var ciudad:String,

){
    override fun toString(): String {
        return "$id $nombres $apellidos $ciudad"
    }
}

