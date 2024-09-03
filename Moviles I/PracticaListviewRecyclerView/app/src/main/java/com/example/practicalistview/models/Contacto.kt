package com.example.practicalistview.models

import java.io.Serializable

class Contacto(
    var id: Int,
    var nombres: String,
    var apellidos: String,
    var telefono: String,
) : Serializable {
    override fun toString(): String {
        return "$nombres $apellidos"
    }
}

