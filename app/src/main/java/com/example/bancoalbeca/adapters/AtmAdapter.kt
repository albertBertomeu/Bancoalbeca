package com.example.bancoalbeca.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bancoalbeca.R
import com.example.bancoalbeca.databinding.ItemAtmBinding
import com.example.bancoalbeca.databinding.ItemPosicionBinding
import com.example.bancoalbeca.entities.CajeroEntity
import com.example.bancoalbeca.pojo.Cuenta

class AtmAdapter(private  var cajeros : List<CajeroEntity>,private val listener :AtmListener):
    RecyclerView.Adapter<AtmAdapter.ViewHolder>(){
    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_atm, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val posicion = cajeros.get(position)
        val id= posicion.id

        with(holder) {
            setListener(id)

            binding.txtCajero1.text= "ATM ${posicion.id}"
            binding.txtCajero2.text= posicion.direccion
        }

    }

    override fun getItemCount(): Int=cajeros.size


    inner class ViewHolder(  view: View): RecyclerView.ViewHolder(view){
        val binding = ItemAtmBinding.bind(view)
        fun setListener(cajeroid: Long) {
            binding.root.setOnClickListener { listener.onClick(cajeroid) }
        }

    }

}
