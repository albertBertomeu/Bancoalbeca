package com.example.bancoalbeca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoalbeca.databinding.WelcomeActivityBinding


class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: WelcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = WelcomeActivityBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)


        binding.btnbienvenida.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}