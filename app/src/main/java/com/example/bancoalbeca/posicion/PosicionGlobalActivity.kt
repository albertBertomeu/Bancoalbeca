package com.example.bancoalbeca.posicion

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration

import com.example.bancoalbeca.databinding.PosicionGlobalActivityBinding
import com.example.bancoalbeca.fragments.AccountsFragment
import com.example.bancoalbeca.fragments.AccountsMovementsFragment
import com.example.bancoalbeca.pojo.Cliente
import com.example.bancoalbeca.pojo.Cuenta
import com.example.bancoalbeca.R
import com.example.bancoalbeca.fragments.CuentaListener
import com.example.bancoalbeca.fragments.MovimientoListener
import com.example.bancoalbeca.pojo.Movimiento
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class PosicionGlobalActivity : AppCompatActivity(), CuentaListener, MovimientoListener {
    private lateinit var binding: PosicionGlobalActivityBinding
    private lateinit var itemDecoration: DividerItemDecoration


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding = PosicionGlobalActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recuperar el cliente
        val clienteLogeado = intent.getSerializableExtra("Cliente") as Cliente
    //Creamos la instancia del fragment

            val frgAccounts = AccountsFragment.newInstance(clienteLogeado)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.posicionCuentas,frgAccounts)
        transaction.addToBackStack(null)
        transaction.commit()
        frgAccounts.setCuentaListener(this)

    }



    override fun onCorreoSeleccionado(cuenta: Cuenta) {
        if (cuenta != null) {
            var hayMovimientos = supportFragmentManager.findFragmentById(R.id.posicionMovimientos) != null
            if (hayMovimientos) {//Se muestra el contenido en la misma Activity
                val movimientosFragment = AccountsMovementsFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.posicionMovimientos, movimientosFragment)
                transaction.commitNow()
                movimientosFragment.setMovimientoListener(this)
                movimientosFragment.mostrarDetalle(cuenta)

            } else {
                val i = Intent(this, PosicionGlobalDetailActivity::class.java)
                i.putExtra("Cuenta", cuenta)
                startActivity(i)
            }
        }
    }
    override fun onMovimientoSeleccionado(movimiento: Movimiento) {
        var layout = layoutInflater.inflate(R.layout.dialog__movement, findViewById(R.id.dialog_alert))
        val txtMsg =layout.findViewById<TextView>(R.id.dialog_text) //aço es per a programar el xml de dialog_movements per a clavar les dades que vols en el id que vols
        txtMsg.text = "Importe: ${movimiento.getImporte()} \n Detalle ${movimiento.getDescripcion()} \n Fecha: ${movimiento.getFechaOperacion()}\n Cuenta origen: ${movimiento.getCuentaOrigen()} \n Cuenta destino${movimiento.getCuentaDestino()}"
        MaterialAlertDialogBuilder(this).setTitle("Detalle del movimiento") .setView(layout) .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, i -> //Código a ejecutar en caso de Aceptar
            dialog.cancel() }) .setCancelable(true)//No podrá desaparecer el diálogo por ningún motivo
            .show()
    }
    }

