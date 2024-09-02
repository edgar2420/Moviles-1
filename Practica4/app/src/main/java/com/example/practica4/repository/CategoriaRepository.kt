package com.moviles.practicanavegacioncasera.repository

import android.content.Context
import com.moviles.practicanavegacioncasera.dal.db.AppDatabase
import com.moviles.practicanavegacioncasera.dal.entities.Categoria

object CategoriaRepository {

    fun getAllRestaurantes(context: Context): List<Categoria> {
        val categoriaDao = AppDatabase.getDatabase(context).categoriaDao()
        return categoriaDao.getAll()
    }

    fun getRestauranteById(id: Int, context: Context): Categoria? {
        val categoriaDao = AppDatabase.getDatabase(context).categoriaDao()
        return categoriaDao.getById(id)
    }

    fun insert(categoria: Categoria, context: Context) {
        val categoriaDao = AppDatabase.getDatabase(context).categoriaDao()
        categoriaDao.insert(categoria)
    }

    fun update(categoria: Categoria, context: Context) {
        val categoriaDao = AppDatabase.getDatabase(context).categoriaDao()
        categoriaDao.update(categoria)
    }

    fun delete(categoria: Categoria, context: Context) {
        val categoriaDao = AppDatabase.getDatabase(context).categoriaDao()
        categoriaDao.delete(categoria)
    }
}