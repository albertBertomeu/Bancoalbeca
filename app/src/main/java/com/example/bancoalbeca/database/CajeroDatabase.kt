package com.example.bancoalbeca.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bancoalbeca.dao.CajeroDao
import com.example.bancoalbeca.entities.CajeroEntity

@Database(entities = arrayOf(CajeroEntity::class), version = 1)
abstract class CajeroDatabase: RoomDatabase() {
    abstract fun cajeroDao(): CajeroDao
}