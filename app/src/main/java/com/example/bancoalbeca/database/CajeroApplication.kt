package com.example.bancoalbeca.database

import android.app.Application
import androidx.room.Room

class CajeroApplication: Application() {
    companion object{
        lateinit var  database: CajeroDatabase

    }

    override fun onCreate() {
        super.onCreate()
        database= Room.databaseBuilder(this, CajeroDatabase::class.java,"CajeroDatabase").build()
    }
}