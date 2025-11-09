package com.example.bancoalbeca

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import com.example.bancoalbeca.bd.MiBancoOperacional
import com.example.bancoalbeca.databinding.TransferActivityBinding
import com.example.bancoalbeca.pojo.Cliente
import com.example.bancoalbeca.pojo.Cuenta

class TransferActivity : AppCompatActivity() {
    private lateinit var binding: TransferActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = TransferActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        val cliente = intent.getSerializableExtra("Cliente") as? Cliente
        if (cliente==null){
            Toast.makeText(this,"El cliente es null",Toast.LENGTH_SHORT).show()
            finish()
        }
        val datos=mbo?.getCuentas(cliente)?:emptyList()
        datos as ArrayList<Cuenta>
            val numerocuenta=mutableListOf<String>()
        for (d in datos){

            val banco=d.getBanco()
            val sucursal=d.getSucursal()
            val dc=d.getDc()
            val cuenta=d.getNumeroCuenta()
            val todo=banco+sucursal+dc+cuenta
            numerocuenta.add(todo)
        }
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, numerocuenta  )
        val spinner= binding.spinnerOpciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Asignar el ArrayAdapter al Spinner
        spinner.adapter = adapter
        binding.rbAjena.setOnClickListener {
            if (binding.rbAjena.isChecked) {
                binding.txtCuenta.visibility = View.VISIBLE
                binding.spinnerOpciones2.visibility= View.INVISIBLE
            }
        }
        binding.rbPropia.setOnClickListener {
            if (binding.rbPropia.isChecked) {
                binding.spinnerOpciones2.visibility = View.VISIBLE
                binding.txtCuenta.visibility = View.INVISIBLE
            }
        }
        binding.btnCancelar.setOnClickListener {
            binding.etCandidad.setText(" ")
            binding.rbAjena.clearFocus()
            binding.rbPropia.clearFocus()
            binding.spinnerOpciones2.visibility= View.INVISIBLE
            binding.txtCuenta.visibility = View.INVISIBLE
            binding.cbJustificante.setChecked(false)
            finish()

        }
        binding.btnEnviar.setOnClickListener {
            val cuentaOrigen=binding.spinnerOpciones.selectedItem.toString()
            var a=""
            if (binding.rbPropia.isChecked){
                val cuentaDestino = binding.spinnerOpciones2.selectedItem.toString()
                 a = "Cuenta propia: $cuentaDestino"
            }else {
                val cuentaAjena = binding.txtCuenta.text.toString()
                a="Cuenta ajena: $cuentaAjena"}
            val importe = binding.etCandidad.text.toString()
            val moneda =binding.moneda.selectedItem.toString()
            var justificante =" "
            if (binding.cbJustificante.isChecked){
                justificante="Enviar justificante"
            }else {justificante="Sin justificante"}

            val toast= Toast.makeText(this,"Cuenta origen: ${cuentaOrigen}\n A $a  Importe: $importe $moneda \n $justificante",
                Toast.LENGTH_LONG )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()

        }

        val spinner2=binding.spinnerOpciones2
        spinner2.adapter=adapter

        val monedas=arrayOf("$","€")
        val adapter2= ArrayAdapter(this,android.R.layout.simple_spinner_item,monedas)
        val spinner3=binding.moneda
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter=adapter2
    }
}