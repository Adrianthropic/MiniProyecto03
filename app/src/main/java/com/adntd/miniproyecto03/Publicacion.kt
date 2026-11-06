package com.adntd.miniproyecto03

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publicaciones")
data class Publicacion(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val titulo: String,
    val descripcion: String,
    val fotoUri: String? = null,
    val fecha: Long = System.currentTimeMillis()
)