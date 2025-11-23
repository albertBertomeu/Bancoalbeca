package com.example.bancoalbeca.adapters

import com.example.bancoalbeca.pojo.Movimiento

interface MovementsListener {
    fun onClick(movimiento: Movimiento)
}