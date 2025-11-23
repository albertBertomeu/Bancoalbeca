package com.example.bancoalbeca.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bancoalbeca.R
import com.example.bancoalbeca.databinding.ItemPosicionBinding
import com.example.bancoalbeca.pojo.Cuenta

class AccountsAdapter(private val cliente: ArrayList<Cuenta>,private val listener: AccountsListener):RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {
    private lateinit var context: Context
    inner class ViewHolder(  view: View): RecyclerView.ViewHolder(view){
        val binding = ItemPosicionBinding.bind(view)
        fun setListener(cuenta: Cuenta) {
            binding.root.setOnClickListener { listener.onClick(cuenta) }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int) : ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_posicion, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: AccountsAdapter.ViewHolder, position: Int) {
        val posicion = cliente.get(position)

        with(holder) {
            setListener(posicion)
            val saldo = posicion.getSaldoActual() ?: 0.0f
            if (saldo < 0.0f) {
                binding.secundari.setTextColor(Color.RED)
            } else {
                binding.secundari.setTextColor(Color.BLACK)
            }
            binding.principal.text = posicion.getNumeroCuenta()

            binding.secundari.text = posicion.getSaldoActual().toString()
        }
    }

    override fun getItemCount(): Int =cliente.size

}