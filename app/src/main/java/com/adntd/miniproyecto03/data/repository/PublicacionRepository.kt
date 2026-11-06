package com.adntd.miniproyecto03.data.repository

import com.adntd.miniproyecto03.data.local.Publicacion
import com.adntd.miniproyecto03.data.local.PublicacionDao
import kotlinx.coroutines.flow.Flow

class PublicacionRepository(private val dao: PublicacionDao) {
    fun obtenerTodas(): Flow<List<Publicacion>> = dao.obtenerTodas()

    suspend fun guardar(publicacion: Publicacion): Long = dao.insertar(publicacion)
}