package com.example.bancoalbeca.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.bancoalbeca.adapters.MovementsAdapter
import com.example.bancoalbeca.adapters.MovementsListener
import com.example.bancoalbeca.bd.MiBancoOperacional
import com.example.bancoalbeca.databinding.FragmentAccountsMovementsBinding
import com.example.bancoalbeca.pojo.Cuenta
import com.example.bancoalbeca.pojo.Movimiento


class AccountsMovementsFragment : Fragment(), MovementsListener {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentAccountsMovementsBinding
    private lateinit var listener: MovimientoListener
    private lateinit var cuenta: Cuenta
    private lateinit var movimientos: ArrayList<Movimiento>
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var movimientosAdapter: MovementsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cuenta =
                it.getSerializable(ARG_CUENTA) as Cuenta
            movimientos =
                it.getSerializable(ARG_MOVIMIENTO) as ArrayList<Movimiento>//no es necesita el objecte ja que la funcio es disparada desde la activity

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsMovementsBinding.inflate(
            inflater, container, false
        )


        movimientosAdapter = MovementsAdapter(movimientos,this) //asi carrega el adapter i li dona el listener per a fer el onClick
        linearLayout = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerMovimientos.apply {
            layoutManager = linearLayout
            adapter = movimientosAdapter
            addItemDecoration(itemDecoration)
            // Inflate the layout for this fragment

        }
        return binding.root
    }



    companion object {
        private const val ARG_CUENTA = "cuenta"
        private const val ARG_MOVIMIENTO = "movimientos"

        @JvmStatic
        fun newInstance(c: Cuenta,m: ArrayList<Movimiento>) =
            AccountsMovementsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CUENTA, c)
                    putSerializable(ARG_MOVIMIENTO,m)
                }
            }
    }
    fun setMovimientoListener(listener: MovimientoListener) { //aço es per a pasarli el listener del fragment a la activity
        this.listener = listener
    }

    override fun onClick(movimiento: Movimiento) { // asi hi han dos coses: 1 la interfas de movimientoListener(listener en el metode on movimientoSeleccionado que esta en la activity)
        // 2 la interfas que obliga a implementar el metode movementListener
        // quan fas click en la activity el fragment recull el listener a traves de movimientoListener i executa el metode que esta asi de la activity
        if (listener != null) {
            listener.onMovimientoSeleccionado(movimiento)
        }
    }
}