package com.example.bancoalbeca.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bancoalbeca.adapters.AccountsAdapter
import com.example.bancoalbeca.adapters.AccountsListener
import com.example.bancoalbeca.bd.MiBancoOperacional
import com.example.bancoalbeca.databinding.FragmentAccountsBinding
import com.example.bancoalbeca.pojo.Cliente
import com.example.bancoalbeca.pojo.Cuenta

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class AccountsFragment : Fragment(), AccountsListener {
    private lateinit var listener: CuentaListener
    private lateinit var itemDecoration: DividerItemDecoration
    private lateinit var cliente: Cliente
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adaptercuenta: AccountsAdapter
    private lateinit var binding: FragmentAccountsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable(ARG_CLIENTE) as Cliente

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsBinding.inflate(
            inflater, container, false
        )

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(requireContext())

        val datos = mbo?.getCuentas(cliente) ?: emptyList()
        datos as ArrayList<Cuenta>

        adaptercuenta = AccountsAdapter(datos, this)
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerId.apply {
            layoutManager = linearLayoutManager
            adapter = adaptercuenta
            addItemDecoration(itemDecoration)
        }
        return binding.root
    }



    fun setCuentaListener(listener: CuentaListener) {
        this.listener = listener
    }

    override fun onClick(cuenta: Cuenta) {
        Toast.makeText(context, "Seleccion: ${cuenta.getNumeroCuenta()}", Toast.LENGTH_LONG).show()
        if (listener != null) {
            listener.onCuentaSeleccionada(cuenta)
        }
    }


    companion object {
        private const val ARG_CLIENTE = "cliente"

        @JvmStatic
        fun newInstance(c: Cliente) = AccountsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_CLIENTE, c)

            }
        }
    }


}
