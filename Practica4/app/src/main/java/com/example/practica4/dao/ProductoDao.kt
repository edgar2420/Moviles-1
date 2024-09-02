package com.moviles.practicanavegacioncasera.dal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.moviles.practicanavegacioncasera.dal.entities.Producto

@Dao
interface ProductoDao {
    @Query("SELECT * FROM producto")
    fun getAll(): List<Producto>

    @Query("SELECT * FROM producto WHERE id = :id")
    fun getById(id: Int): Producto?

    @Insert
    fun insert(Hamburguesa: Producto)

    @Update
    fun update(Hamburguesa: Producto)

    @Delete
    fun delete(Hamburguesa: Producto)

    @Query("SELECT * FROM producto WHERE idCategoria = :idCategoria")
    fun getByCategoria(idCategoria: Int): List<Producto>
}