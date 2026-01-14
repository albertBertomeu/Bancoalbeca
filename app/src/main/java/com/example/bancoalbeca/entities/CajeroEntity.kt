package com.example.bancoalbeca.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class CajeroEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var direccion: String="",
    var latitud: Double=0.0,
    var longitud: Double=0.0,
    var zoom: String=""
){}


