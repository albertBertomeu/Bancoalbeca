package com.example.bancoalbeca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bancoalbeca.adapters.AtmAdapter
import com.example.bancoalbeca.adapters.AtmListener
import com.example.bancoalbeca.database.CajeroApplication
import com.example.bancoalbeca.databinding.ActivityAtmListBinding
import com.example.bancoalbeca.entities.CajeroEntity

import java.util.concurrent.LinkedBlockingQueue

class AtmListActivity : AppCompatActivity(), AtmListener {
    private lateinit var binding: ActivityAtmListBinding
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var atmAdapter: AtmAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atm_list)
        binding= ActivityAtmListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val queue =LinkedBlockingQueue<MutableList<CajeroEntity>>() //aço es fa per a ordenar els fils
        Thread{
            val all = CajeroApplication.database.cajeroDao().getAllCajeros()
            queue.add(all)
        }.start()
        atmAdapter= AtmAdapter((queue.take()),this)

        itemDecoration= DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        linearLayout = LinearLayoutManager(this)
        binding.recicleratm.apply {
            layoutManager=linearLayout
            adapter=atmAdapter
            addItemDecoration(itemDecoration)
        }
        }

    override fun onClick(cajeroid: Long) {

        val intent = Intent(this, AtmFormActivity::class.java)
        intent.putExtra("banc",cajeroid)
        val editable = true
        intent.putExtra("afegir",editable)
        startActivity(intent)
    }

}
