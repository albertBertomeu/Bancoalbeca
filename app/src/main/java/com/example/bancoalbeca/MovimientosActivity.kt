package com.example.bancoalbeca

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bancoalbeca.bd.MiBancoOperacional
import com.example.bancoalbeca.databinding.MovimientosActivityBinding
import com.example.bancoalbeca.pojo.Cliente
import com.example.bancoalbeca.pojo.Cuenta
import com.example.bancoalbeca.pojo.Movimiento

class MovimientosActivity : AppCompatActivity() {
    private lateinit var binding: MovimientosActivityBinding
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var movimientosadapter: MovimientosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = MovimientosActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente

        val mbo: MiBancoOperacional? = MiBancoOperacional.Companion.getInstance(this)
        val cuentas=mbo?.getCuentas(cliente)?:emptyList()

        val spinner =binding.sMovimientos
        val adapt = ArrayAdapter(this,android.R.layout.simple_spinner_item, cuentas  )
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Asignar el ArrayAdapter al Spinner
        spinner.adapter = adapt

        linearLayout= LinearLayoutManager(this)
        movimientosadapter = MovimientosAdapter(arrayListOf())
        itemDecoration= DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.recycler.apply {
            layoutManager = linearLayout
            adapter = movimientosadapter
            addItemDecoration(itemDecoration)
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
             {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val cuenta = parent.getItemAtPosition(position) as? Cuenta
                val movimientos = ArrayList(mbo?.getMovimientos(cuenta) ?: emptyList())
                movimientos as ArrayList<Movimiento>
                movimientosadapter.updateData(movimientos)


            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }



        }



}
