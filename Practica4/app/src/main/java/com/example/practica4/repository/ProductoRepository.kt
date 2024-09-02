package com.moviles.practicanavegacioncasera.repository

import android.content.Context
import com.moviles.practicanavegacioncasera.dal.db.AppDatabase
import com.moviles.practicanavegacioncasera.dal.entities.Producto

object ProductoRepository {
    fun getAllHamburguesas(context: Context): List<Producto> {
        val productoDao = AppDatabase.getDatabase(context).productoDao()
        return productoDao.getAll()
    }

    fun getHamburguesaById(id: Int, context: Context): Producto? {
        val productoDao = AppDatabase.getDatabase(context).productoDao()
        return productoDao.getById(id)
    }

    fun getHamburguesaByRestaurante(idCategoria: Int, context: Context): List<Producto> {
        val productoDao = AppDatabase.getDatabase(context).productoDao()
        return productoDao.getByCategoria(idCategoria)
    }

    fun insert(producto: Producto, context: Context): List<Int> {
        val productoDao = AppDatabase.getDatabase(context).productoDao()
        productoDao.insert(producto)
        val listhamburguesa = productoDao.getAll()
        val listId = ArrayList<Int>()
        for (producto in listhamburguesa) {
            listId.add(producto.id)
        }
        return listId
    }

    fun update(producto: Producto, context: Context) {
        val hamburguesaDao = AppDatabase.getDatabase(context).productoDao()
        hamburguesaDao.update(producto)
    }

    fun delete(producto: Producto, context: Context) {
        val productoDao = AppDatabase.getDatabase(context).productoDao()
        productoDao.delete(producto)
    }
}