package com.adntd.miniproyecto03.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Publicacion::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun publicacionDao(): PublicacionDao
}