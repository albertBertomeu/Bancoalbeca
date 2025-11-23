package com.example.bancoalbeca.fragments

import com.example.bancoalbeca.pojo.Cuenta
import com.example.bancoalbeca.pojo.Movimiento

interface MovimientoListener {
    fun onMovimientoSeleccionado(movimiento: Movimiento)
}