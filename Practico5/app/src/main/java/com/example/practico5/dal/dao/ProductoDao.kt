package com.example.practico5.dal.conn

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practicasqlite.dal.dto.CategoriaConProducto
import com.example.practicasqlite.dal.dto.Producto

@Dao
interface ProductoDao {
    @Query("SELECT * FROM producto")
    fun getAll(): List<Producto>

    @Query("SELECT * FROM producto WHERE id IN (:id)")
    fun getById(id: Int): Producto?

    @Insert
    fun insert(user: Producto)

    @Update
    fun update(user: Producto)

    @Delete
    fun delete(user: Producto)

    @Query("SELECT * FROM producto WHERE idCategoria = :idCategoria")
    fun getByCategoria(idCategoria: Int): List<Producto>
}