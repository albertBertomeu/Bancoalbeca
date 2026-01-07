package com.example.bancoalbeca.posicion

import android.content.DialogInterface
import android.hardware.Camera
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.example.bancoalbeca.R
import com.example.bancoalbeca.bd.MiBancoOperacional
import com.example.bancoalbeca.databinding.PosicionGlobalActivityDetailsBinding
import com.example.bancoalbeca.fragments.AccountsMovementsFragment
import com.example.bancoalbeca.fragments.MovimientoListener
import com.example.bancoalbeca.pojo.Cuenta
import com.example.bancoalbeca.pojo.Movimiento
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PosicionGlobalDetailActivity : AppCompatActivity(), MovimientoListener {
    private lateinit var binding: PosicionGlobalActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PosicionGlobalActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        val cuenta = intent.getSerializableExtra("Cuenta") as Cuenta
        var movimientosRaw = mbo?.getMovimientos(cuenta)
        var movimientos = ArrayList(movimientosRaw?.filterIsInstance<Movimiento>() ?: emptyList())

        binding.navigationBar.setOnNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.todos -> {
                    movimientosRaw = mbo?.getMovimientos(cuenta)
                    movimientos = ArrayList(movimientosRaw?.filterIsInstance<Movimiento>() ?: emptyList())
                    cambiarFragment(cuenta,movimientos)

                }

                R.id.tipo0 -> {
                    movimientosRaw = mbo?.getMovimientosTipo(cuenta, 0)
                    movimientos = ArrayList(movimientosRaw?.filterIsInstance<Movimiento>() ?: emptyList())

                    cambiarFragment(cuenta,movimientos)

                }

                R.id.tipo1 -> {
                    movimientosRaw = mbo?.getMovimientosTipo(cuenta, 1)
                    movimientos = ArrayList(movimientosRaw?.filterIsInstance<Movimiento>() ?: emptyList())
                    cambiarFragment(cuenta,movimientos)
                }

                R.id.tipo2 -> {
                    movimientosRaw = mbo?.getMovimientosTipo(cuenta, 2)
                    movimientos = ArrayList(movimientosRaw?.filterIsInstance<Movimiento>() ?: emptyList())
                   cambiarFragment(cuenta,movimientos)
                }
                R.id.salir->{finish()}
            }
            false
        }
        if (cuenta != null) {
            val movimientosFragment = AccountsMovementsFragment.newInstance(
                cuenta,
                movimientos
            ) //aço li pasa la cuenta al fragment
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.posicionMovimientos, movimientosFragment)
            transaction.addToBackStack(null)
            transaction.commit() //aço reemplaça el id del xml pel fragment
            movimientosFragment.setMovimientoListener(this) //aço es per a donarli el listener a la activity ja que en el fragment no deu de anar i lil passa al fragment

            // Usar postDelayed para asegurar que el fragment esté listo

        }

    }
    fun cambiarFragment(cuenta:Cuenta, movimientos: ArrayList<Movimiento>){

        val movimientosFragment = AccountsMovementsFragment.newInstance(
            cuenta,
            movimientos
        ) //aço li pasa la cuenta al fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.posicionMovimientos, movimientosFragment)
        transaction.addToBackStack(null)
        transaction.commit() //aço reemplaça el id del xml pel fragment
        movimientosFragment.setMovimientoListener(this)
    }
    override fun onMovimientoSeleccionado(movimiento: Movimiento) { //esta funcion es la de la interface movimiento listener encarregada de carregar el dialog
        var layout =
            layoutInflater.inflate(R.layout.dialog__movement, findViewById(R.id.dialog_alert))
        val txtMsg =
            layout.findViewById<TextView>(R.id.dialog_text) //aço es per a programar el xml de dialog_movements per a clavar les dades que vols en el id que vols
        txtMsg.text =
            "Importe: ${movimiento.getImporte()} \n Detalle ${movimiento.getDescripcion()} \n Fecha: ${movimiento.getFechaOperacion()}\n Cuenta origen: ${movimiento.getCuentaOrigen()} \n Cuenta destino${movimiento.getCuentaDestino()}"
        MaterialAlertDialogBuilder(this).setTitle("Detalle del movimiento").setView(layout)
            .setPositiveButton(
                "Aceptar",
                DialogInterface.OnClickListener { dialog, i -> //Código a ejecutar en caso de Aceptar
                    dialog.cancel()
                }).setCancelable(true)//No podrá desaparecer el diálogo por ningún motivo
            .show()
    }
}
