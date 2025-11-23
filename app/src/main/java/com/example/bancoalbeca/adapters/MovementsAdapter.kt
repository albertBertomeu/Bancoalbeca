package com.example.bancoalbeca.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bancoalbeca.R
import com.example.bancoalbeca.databinding.ItemMovimientosBinding
import com.example.bancoalbeca.pojo.Cuenta
import com.example.bancoalbeca.pojo.Movimiento

class MovementsAdapter(private val movimientos: ArrayList<Movimiento>,private val listener: MovementsListener) : RecyclerView.Adapter<MovementsAdapter.ViewHolder>(){


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemMovimientosBinding.bind(view)
        fun setListener(movimiento: Movimiento) {
            binding.root.setOnClickListener { listener.onClick(movimiento) } //el metodo setOnClickListener es un metodo de view y define un listener que el tens que implementar de ahi la necesitat de la interface
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_movimientos,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posicion=movimientos.get(position)
        with(holder){
            setListener(posicion)
            binding.principalmovimientos.text=posicion.getCuentaDestino().toString()
            binding.secundarimovimientos.text="${posicion.getDescripcion()}\n importe ${posicion.getImporte()}"

        }
    }


    override fun getItemCount(): Int {
        return movimientos.size
    }

}