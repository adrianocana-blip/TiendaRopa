package com.example.tiendaropa

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PrendaDao {
    @Insert
    suspend fun insertar(prenda: Prenda)

    @Query("SELECT * FROM prendas ORDER BY id DESC")
    suspend fun obtenerTodas(): List<Prenda>

    @Delete
    suspend fun borrar(prenda: Prenda)
}