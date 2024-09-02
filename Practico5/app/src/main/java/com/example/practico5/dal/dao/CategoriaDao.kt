package com.example.practico5.dal.conn

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.practicasqlite.dal.dto.Categoria
import com.example.practicasqlite.dal.dto.CategoriaConProducto

@Dao
interface CategoriaDao {
    @Query("SELECT * FROM categoria")
    fun getAll(): List<Categoria>

    @Query("SELECT * FROM categoria WHERE id IN (:id)")
    fun getById(id: Int): Categoria?

    @Transaction
    @Query("SELECT * FROM categoria")
    fun getProductoWithCategoria(): List<CategoriaConProducto>

    @Insert
    fun insert(user: Categoria)

    @Update
    fun update(user: Categoria)

    @Delete
    fun delete(user: Categoria)
}