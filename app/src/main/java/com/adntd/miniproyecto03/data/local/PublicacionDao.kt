package com.adntd.miniproyecto03.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PublicacionDao {
    @Insert
    suspend fun insertar(publicacion: Publicacion): Long

    @Query("SELECT * FROM publicaciones ORDER BY fecha DESC")
    fun obtenerTodas(): Flow<List<Publicacion>>

    @Query("SELECT * FROM publicaciones WHERE id = :id")
    suspend fun obtenerPorId(id: Long): Publicacion?
}