package com.example.bancoalbeca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoalbeca.databinding.LoginActivityBinding
import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnEntrar.setOnClickListener {
            val valid=entrar()
            if (valid) {
             val dni=binding.tiUsuario.text.toString()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("dni",dni)
                startActivity(intent)
                finish()



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

