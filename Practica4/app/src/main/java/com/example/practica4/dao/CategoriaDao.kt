package com.moviles.practicanavegacioncasera.dal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.moviles.practicanavegacioncasera.dal.entities.Categoria

@Dao
interface CategoriaDao {
    @Query("SELECT * FROM categoria")
    fun getAll(): List<Categoria>

    @Query("SELECT * FROM categoria WHERE id = :id")
    fun getById(id: Int): Categoria?

    @Insert
    fun insert(Categoria: Categoria)

    @Update
    fun update(Categoria: Categoria)

    @Delete
    fun delete(Categoria: Categoria)


}