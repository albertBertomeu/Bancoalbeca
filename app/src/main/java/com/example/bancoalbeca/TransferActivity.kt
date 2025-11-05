package com.example.bancoalbeca

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoalbeca.dao.CuentaDAO
import com.example.bancoalbeca.databinding.TransferActivityBinding
import com.example.bancoalbeca.pojo.Cliente

class TransferActivity : AppCompatActivity() {
    private lateinit var binding: TransferActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = TransferActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente

        val idCliente = cliente?.getId()
         val datos = CuentaDAO.getCuentas(cliente)

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,datos)
val spinner= binding.spinnerOpciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Asignar el ArrayAdapter al Spinner
        spinner.adapter = adapter
    }
}