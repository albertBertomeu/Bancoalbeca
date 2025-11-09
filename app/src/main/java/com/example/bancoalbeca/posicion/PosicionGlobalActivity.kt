package com.example.bancoalbeca.posicion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bancoalbeca.bd.MiBancoOperacional
import com.example.bancoalbeca.databinding.PosicionGlobalActivityBinding
import com.example.bancoalbeca.pojo.Cliente
import com.example.bancoalbeca.pojo.Cuenta

class PosicionGlobalActivity : AppCompatActivity() {
    private lateinit var binding: PosicionGlobalActivityBinding
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var posicionAdapter: PosicionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        itemDecoration= DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding = PosicionGlobalActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente

        val mbo: MiBancoOperacional? = MiBancoOperacional.Companion.getInstance(this)
        val datos=mbo?.getCuentas(cliente)?:emptyList()
        datos as ArrayList<Cuenta>

        posicionAdapter = PosicionAdapter(datos)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerId.apply{
            layoutManager=linearLayoutManager
            adapter=posicionAdapter
            addItemDecoration(itemDecoration)
        }

    }
}