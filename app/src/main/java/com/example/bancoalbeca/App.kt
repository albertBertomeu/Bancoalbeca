package com.example.bancoalbeca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import com.example.bancoalbeca.databinding.LoginActivityBinding
import com.example.bancoalbeca.databinding.MainActivityBinding
import com.google.android.material.snackbar.Snackbar


class App : AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    private lateinit var binding2: MainActivityBinding



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding2= MainActivityBinding.inflate(layoutInflater)


        binding.btnEntrar.setOnClickListener {
            val valid=entrar()
            if (valid) {
             val dni=binding.tiUsuario.text.toString()
                setContentView(binding2.root)
                binding2.text.setText(" Bienvenido/a ${dni}")

            }

        }
        binding.btnSalir.setOnClickListener { finishAffinity() }


    }



    fun entrar(): Boolean{
        val usuario = binding.tiUsuario.text.toString()
        val pasword= binding.tiPassword.text.toString()
        var esValid=true
        if (usuario.isEmpty()) {
            Snackbar.make(
                binding.root,
                "Error tienes que poner el nombre",
                Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.tilUsuario).show()
            esValid=false
        }
        if (pasword.isEmpty()){
            Snackbar.make(
                binding.root,
                "Error tienes que poner la contraseña",
                Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.tilPassword).show()
            esValid=false
        }
        if (usuario.isEmpty()&&pasword.isEmpty()){
            Snackbar.make(
                binding.root,
                "Error tienes que poner el nombre y la contraseña",
                Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.tilPassword).show()
            esValid=false
        }
        return esValid

    }
}

