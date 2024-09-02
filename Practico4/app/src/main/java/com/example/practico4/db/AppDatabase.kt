package com.moviles.practicanavegacioncasera.dal.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.moviles.practicanavegacioncasera.dal.dao.CategoriaDao
import com.moviles.practicanavegacioncasera.dal.dao.ProductoDao
import com.moviles.practicanavegacioncasera.dal.entities.Categoria
import com.moviles.practicanavegacioncasera.dal.entities.Producto


@Database(entities = [Categoria::class,Producto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoriaDao(): CategoriaDao
    abstract fun productoDao(): ProductoDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "practica_bd_room"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}
