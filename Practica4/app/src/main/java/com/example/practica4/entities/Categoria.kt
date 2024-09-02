package com.moviles.practicanavegacioncasera.dal.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categoria(
    val nombre: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    override fun toString(): String {
        return nombre
    }
}