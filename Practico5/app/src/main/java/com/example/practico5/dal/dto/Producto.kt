package com.example.practico5.dal.conn

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Producto(
    val nombre: String,
    val precio: Double,
    val idCategoria: Int
) {
    @PrimaryKey
    var id: Int? = null
}
