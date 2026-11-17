package com.example.tiendaropa

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Prenda::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun prendaDao(): PrendaDao

    companion object {
        @Volatile
        private var instancia: AppDatabase? = null

        fun obtener(context: Context): AppDatabase {
            return instancia ?: synchronized(this) {
                val nueva = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tienda_ropa_db"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                instancia = nueva
                nueva
            }
        }
    }
}