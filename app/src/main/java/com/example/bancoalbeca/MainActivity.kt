package com.example.bancoalbeca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoalbeca.databinding.MainActivityBinding
import com.example.bancoalbeca.pojo.Cliente

class MainActivity: AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val client= intent.getSerializableExtra("Cliente") as? Cliente
        binding.text.text = "bienvenido/a ${client?.getNombre()}"
        binding.salir.setOnClickListener{
            val intent= Intent(this,WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.cambiar.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.transferencia.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            intent.putExtra("Cliente",client)
        }
    }
}