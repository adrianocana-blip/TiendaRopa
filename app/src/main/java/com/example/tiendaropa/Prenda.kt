package com.example.tiendaropa

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prendas")
data class Prenda(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val talla: String,
    val precio: String
)