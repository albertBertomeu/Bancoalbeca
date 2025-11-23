package com.example.bancoalbeca

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.bancoalbeca.databinding.ItemMovimientosBinding
import com.example.bancoalbeca.pojo.Movimiento


class MovimientosAdapter(private val movimientos: ArrayList<Movimiento>) : RecyclerView.Adapter<MovimientosAdapter.ViewHolder>(){

    private lateinit var context: Context

    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val binding = ItemMovimientosBinding.bind(view)

    }


    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int ): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_movimientos,parent,false)
       return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posicion=movimientos.get(position)
        with(holder){
            binding.principalmovimientos.text=posicion.getCuentaDestino().toString()
            binding.secundarimovimientos.text="${posicion.getDescripcion()}\n importe ${posicion.getImporte()}"

        }
    }


    override fun getItemCount(): Int {
       return movimientos.size
    }

}