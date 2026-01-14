package com.example.bancoalbeca

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bancoalbeca.database.CajeroApplication
import com.example.bancoalbeca.databinding.FragmentAtmEditarBinding
import com.example.bancoalbeca.entities.CajeroEntity
import java.util.concurrent.LinkedBlockingQueue


class Atm_editar : Fragment() {

    private lateinit var binding: FragmentAtmEditarBinding
    private lateinit var mActivity: AtmFormActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAtmEditarBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as AtmFormActivity
        mActivity.setSupportActionBar(binding.toolbar)
        mActivity.supportActionBar?.setTitle("Editar ATM")


        binding.cancelar.setOnClickListener { mActivity.finish() }
        binding.aceptar.setOnClickListener {
            val direccion = binding.direccion.text.toString()
            val longitud = binding.longitud.text.toString().toDouble()
            val latitud = binding.latitudP.text.toString().toDouble()
            val cajero = CajeroEntity(0, direccion, latitud, longitud)
            Thread {
                CajeroApplication.database.cajeroDao().addCajero(cajero)
            }.start()

            mActivity.finish()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Atm_editar().apply {
                arguments = Bundle().apply {

                }
            }
    }
}