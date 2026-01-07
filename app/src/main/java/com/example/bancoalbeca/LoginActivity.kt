package com.example.bancoalbeca

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoalbeca.databinding.LoginActivityBinding
import com.example.bancoalbeca.bd.MiBancoOperacional
import com.example.bancoalbeca.pojo.Cliente


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnEntrar.setOnClickListener {
            val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

            // Introducimos los datos como si fuera la pantalla inicial
            var cliente = Cliente()
            cliente.setNif(binding.tiUsuario.text.toString())
                cliente.setClaveSeguridad(binding.tiPassword.text.toString())

            if(entrar()) {
                // Logueamos al cliente
                val clienteLogeado = mbo?.login(cliente) ?: -1

                if (clienteLogeado == -1) {
                    Toast.makeText(this, "El cliente no existe en la BD", Toast.LENGTH_LONG).show()
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("Cliente", clienteLogeado)
                    startActivity(intent)


                }
            }



            }
        binding.btnSalir.setOnClickListener { finishAffinity() }

    }



    fun entrar(): Boolean {
        val usuario = binding.tiUsuario.text.toString()
        val password = binding.tiPassword.text.toString()
        binding.tilUsuario.error =null
        binding.tilPassword.error =null
        var esValid = true

        if (usuario.isEmpty()) {
            binding.tilUsuario.error = "Has d'introduir el nom d'usuari"
            esValid = false
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Has d'introduir la contrasenya"
            esValid = false
        }

        return esValid
    }
}

