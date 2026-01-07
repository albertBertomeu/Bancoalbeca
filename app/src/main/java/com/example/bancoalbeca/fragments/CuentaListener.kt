package com.example.bancoalbeca.fragments

import com.example.bancoalbeca.pojo.Cuenta

interface CuentaListener {
    fun onCuentaSeleccionada(cuenta: Cuenta)
}