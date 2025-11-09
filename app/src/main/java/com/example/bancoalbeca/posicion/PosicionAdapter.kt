package com.example.bancoalbeca.posicion

import com.example.bancoalbeca.R
import android.content.Context
import android.graphics.Color
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.bancoalbeca.databinding.ItemPosicionBinding
import com.example.bancoalbeca.pojo.Cliente
import com.example.bancoalbeca.pojo.Cuenta

class PosicionAdapter(private val cliente: ArrayList<Cuenta>):RecyclerView.Adapter<PosicionAdapter.ViewHolder>() {
   private lateinit var context: Context
    inner class ViewHolder(  view: View): RecyclerView.ViewHolder(view){
        val binding = ItemPosicionBinding.bind(view)
    }



    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int) : ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_posicion, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: PosicionAdapter.ViewHolder, position: Int) {
        val posicion = cliente.get(position)

        with(holder) {
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