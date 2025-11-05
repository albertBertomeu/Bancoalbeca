package com.example.bancoalbeca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoalbeca.databinding.ChangePasswordActivityBinding

class ChangePasswordActivity: AppCompatActivity() {
    private lateinit var binding: ChangePasswordActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ChangePasswordActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}