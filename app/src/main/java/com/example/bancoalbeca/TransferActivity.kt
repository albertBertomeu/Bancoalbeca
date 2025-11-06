package com.example.bancoalbeca

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoalbeca.bd.MiBancoOperacional
import com.example.bancoalbeca.databinding.TransferActivityBinding
import com.example.bancoalbeca.pojo.Cliente
import com.example.bancoalbeca.pojo.Cuenta

class TransferActivity : AppCompatActivity() {
    private lateinit var binding: TransferActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = TransferActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        val cliente = intent.getSerializableExtra("Cliente") as? Cliente
        if (cliente==null){
            Toast.makeText(this,"El cliente es null",Toast.LENGTH_SHORT).show()
            finish()
        }
        val datos=mbo?.getCuentas(cliente)?:emptyList()
        datos as ArrayList<Cuenta>
            val numerocuenta=mutableListOf<String>()
        for (d in datos){

            val banco=d.getBanco()
            val sucursal=d.getSucursal()
            val dc=d.getDc()
            val cuenta=d.getNumeroCuenta()
            val todo=banco+sucursal+dc+cuenta
            numerocuenta.add(todo)
        }
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, numerocuenta  )
        val spinner= binding.spinnerOpciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Asignar el ArrayAdapter al Spinner
        spinner.adapter = adapter
    }
}