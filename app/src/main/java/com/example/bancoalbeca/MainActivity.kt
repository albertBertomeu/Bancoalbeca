package com.example.bancoalbeca

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.bancoalbeca.databinding.MainActivityBinding
import com.example.bancoalbeca.pojo.Cliente
import com.example.bancoalbeca.posicion.PosicionGlobalActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout = binding.drawerlayout
        val navView = binding.navView
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Esto oculta el título de la app en la Toolbar }

// Configura el botón de hamburguesa
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout as DrawerLayout?, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

// Listener para los ítems del menú
        navView?.setNavigationItemSelectedListener(this)

        super.onCreate(savedInstanceState)

val client = intent.getSerializableExtra("Cliente") as? Cliente

        binding.text.text = "bienvenido/a ${client?.getNombre()}"

        binding.salir.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.cambiar.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)

        }
        binding.transferencia.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            intent.putExtra("Cliente", client)
            startActivity(intent)
        }
        binding.posicion.setOnClickListener {
            val intent = Intent(this, PosicionGlobalActivity::class.java)
            intent.putExtra("Cliente", client)
            startActivity(intent)

        }
        binding.movimientos.setOnClickListener {
            val intent = Intent(this, MovimientosActivity::class.java)
            intent.putExtra("Cliente", client)
            startActivity(intent)

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val client = intent.getSerializableExtra("Cliente") as? Cliente

        when(item.itemId){

            R.id.nav_globalPosition->{
                val intent = Intent(this, PosicionGlobalActivity::class.java)
                intent.putExtra("Cliente", client)
                startActivity(intent)

            }
            R.id.nav_home->{
                val intent=Intent (this, MainActivity::class.java)
                intent.putExtra("Cliente",client)
                startActivity(intent)
                finish()

            }
            R.id.nav_settings->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)


            }

        }
        return true
    }
}