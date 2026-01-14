package com.example.bancoalbeca

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.bancoalbeca.database.CajeroApplication
import com.example.bancoalbeca.databinding.FragmentAtmVisualizarBinding
import com.example.bancoalbeca.entities.CajeroEntity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.concurrent.LinkedBlockingQueue

class Atm_visualizar() : Fragment() {
private lateinit var binding: FragmentAtmVisualizarBinding
    private lateinit var cajero2: CajeroEntity
    private var mActivity: AtmFormActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAtmVisualizarBinding.inflate(
            inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mActivity = activity as? AtmFormActivity
        mActivity?.setSupportActionBar(binding.toolbar)
        mActivity?.supportActionBar?.setTitle("Visualizar ATM")
        setHasOptionsMenu(true)
        val queue= LinkedBlockingQueue<CajeroEntity>()
        Thread{
            val id = arguments?.getLong("id")
            val cajero= CajeroApplication.database.cajeroDao().getCajerosById(id)
            queue.add(cajero)
        }.start()
        cajero2= queue.take()
        binding.direccion.hint= cajero2.direccion
        binding.latitudP.hint=cajero2.latitud.toString()
        binding.longitud.hint=cajero2.longitud.toString()
        binding.cancelar.setOnClickListener {mActivity?.finish() }
        binding.aceptar.setOnClickListener {}
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_atm, menu)
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
          with(cajero){
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
          val intent = Intent(requireContext(), AtmListActivity::class.java)
          startActivity(intent)
          mActivity?.finish()
    }

    fun dialogo(cajero: CajeroEntity){
        var layout= layoutInflater.inflate(R.layout.dialog__movement,mActivity?.findViewById(R.id.dialog_alert))
        val txtMsg= layout.findViewById<TextView>(R.id.dialog_text)
        txtMsg.text="Estas seguro que quieres borrar este cajero?"
        MaterialAlertDialogBuilder(requireContext()).setTitle("Borrar Cajero").setView(layout).setPositiveButton("OK",{
                dialog,i->
            Thread{
                CajeroApplication.database.cajeroDao().deleteCajero(cajero)

            }.start()
            val intent = Intent(requireContext(), AtmListActivity::class.java)
            startActivity(intent)
            mActivity?.finish()
        }).setNegativeButton("Cancel",{dialog, i -> //Código a ejecutar en caso de Aceptar
            dialog.cancel()}).setCancelable(true).show()
    }
    companion object {
        private const val ARG_ID = "id"
        @JvmStatic
        fun newInstance(id:Long) =
            Atm_visualizar().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ID,id)
                }
            }
    }
}