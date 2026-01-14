package com.example.bancoalbeca

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

import com.example.bancoalbeca.database.CajeroApplication
import com.example.bancoalbeca.databinding.ActivityAtmFormBinding
import com.example.bancoalbeca.entities.CajeroEntity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.concurrent.LinkedBlockingQueue

class AtmFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAtmFormBinding
    private lateinit var cajero2: CajeroEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val afegir = intent.getSerializableExtra("afegir") as Boolean
        if (afegir){
            val id = intent.getSerializableExtra("banc") as Long
            val fragment = Atm_visualizar.newInstance(id)
            val fragment2 = supportFragmentManager.beginTransaction()
            fragment2.replace(R.id.atm_fragment, fragment)
            fragment2.addToBackStack(null).commit()
        }else{
            val fragment= Atm_editar.newInstance()
            val fragment2= supportFragmentManager.beginTransaction()
            fragment2.replace(R.id.atm_fragment,fragment).addToBackStack(null).commit()
        }





    }
    /*
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    supportActionBar?.setTitle("Visualizar ATM")

    val queue= LinkedBlockingQueue<CajeroEntity>()
    Thread{
        val banc = intent.getSerializableExtra("banc") as Long
       val cajero= CajeroApplication.database.cajeroDao().getCajerosById(banc)
        queue.add(cajero)
    }.start()
    cajero2= queue.take()
    binding.direccion.hint= cajero2.direccion.toString()
    binding.latitudP.hint=cajero2.latitud.toString()
    binding.longitud.hint=cajero2.longitud.toString()
binding.cancelar.setOnClickListener { finish() }
    binding.aceptar.setOnClickListener {




}
override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_atm, menu)
    return true
}
override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.opcion1 -> {dialogo(cajero2)

        }
        R.id.opcion2 -> { update(cajero2) }
    }
    return super.onOptionsItemSelected(item)
}

private fun update(cajero: CajeroEntity) {
  /*  with(cajero){
        if (!binding.direccion.text.isEmpty()){
            direccion=binding.direccion.text.toString()

        }
        if (!binding.latitudP.text.isEmpty()){
            latitud=binding.latitudP.text.toString().toDouble()
        }
        if (!binding.longitud.text.isEmpty()){
            longitud=binding.longitud.text.toString().toDouble()
        }


    }
    Thread{
        CajeroApplication.database.cajeroDao().updateCajero(cajero)
    }.start()
    val intent = Intent(this, AtmListActivity::class.java)
    startActivity(intent)
    finish()*/
}

fun dialogo(cajero: CajeroEntity){
    var layout= layoutInflater.inflate(R.layout.dialog__movement,findViewById(R.id.dialog_alert))
    val txtMsg= layout.findViewById<TextView>(R.id.dialog_text)
    txtMsg.text="Estas seguro que quieres borrar este cajero?"
    MaterialAlertDialogBuilder(this).setTitle("Borrar Cajero").setView(layout).setPositiveButton("OK",{
            dialog,i->
        Thread{
            CajeroApplication.database.cajeroDao().deleteCajero(cajero)

        }.start()
        val intent = Intent(this, AtmListActivity::class.java)
        startActivity(intent)
        finish()
    }).setNegativeButton("Cancel",{dialog, i -> //Código a ejecutar en caso de Aceptar
        dialog.cancel()}).setCancelable(true).show()
}
}*/
}
