package com.example.bancoalbeca

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoalbeca.databinding.MainActivityBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding2: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding2= MainActivityBinding.inflate(layoutInflater)
        setContentView(binding2.root)
        val dni= intent.getStringExtra("dni")
        binding2.text.text = getString(R.string.bienvenido,dni)
    }
}