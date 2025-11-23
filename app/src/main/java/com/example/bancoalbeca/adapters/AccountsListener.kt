package com.example.bancoalbeca.adapters

import com.example.bancoalbeca.pojo.Cuenta
import com.example.bancoalbeca.pojo.Movimiento

interface AccountsListener {
    fun onClick(cuenta: Cuenta)
}