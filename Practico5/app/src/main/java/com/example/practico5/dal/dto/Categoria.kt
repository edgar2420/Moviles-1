package com.example.practico5.dal.conn

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categoria(
    val nombre: String
) {
    @PrimaryKey
    var id: Int? = null
    override fun toString(): String {
        return nombre
    }
}
