package com.example.bancoalbeca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoalbeca.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root) // Reproduce la animación Lottie
        binding.animationView.playAnimation() // Configura un temporizador para mostrar la pantalla de inicio durante un cierto tiempo
        val splashScreenDuration = 3000 // 3 segundos
        val intent = Intent(
            this,
            WelcomeActivity::class.java
        )  // Inicia la actividad principal después del tiempo especificado
        binding.animationView.postDelayed(
            { startActivity(intent)
                finish () },
            splashScreenDuration.toLong()
        )
    }
}