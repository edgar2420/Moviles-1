package com.moviles.practicanavegacioncasera.dal.entities

import android.content.Context
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.moviles.practicanavegacioncasera.repository.CategoriaRepository

@Entity(
    foreignKeys = [ForeignKey(
        entity = Categoria::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idCategoria"),
        onDelete = CASCADE
    )]
)
data class Producto(
    val nombre: String,
    val descripcion: String,
    val precio_actual: String,
    val idCategoria: Int
) {
    fun getCategoria(context: Context): Categoria? {
        return CategoriaRepository.getRestauranteById(idCategoria, context)
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {
        return nombre
    }
}

