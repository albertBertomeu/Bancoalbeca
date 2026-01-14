package com.example.bancoalbeca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoalbeca.database.CajeroApplication
import com.example.bancoalbeca.databinding.ActivityManagementAtmBinding
import com.example.bancoalbeca.entities.CajeroEntity

class AtmManagementActivity : AppCompatActivity() {
    private lateinit var intent : Intent
    private lateinit var binding: ActivityManagementAtmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityManagementAtmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cajerosEntityLists: List<CajeroEntity> = listOf(
            CajeroEntity(
                1,
                "Carrer del Clariano, 1, 46021 Valencia, Valencia, España",
                39.47600769999999,
                -0.3524475000000393,
                ""
            ),
            CajeroEntity(
                2,
                "Avinguda del Cardenal Benlloch, 65, 46021 València, Valencia, España",
                39.4710366,
                -0.3547525000000178,
                ""
            ),
            CajeroEntity(
                3,
                "Av. del Port, 237, 46011 València, Valencia, España",
                39.46161999999999,
                -0.3376299999999901,
                ""
            ),
            CajeroEntity(
                4,
                "Carrer del Batxiller, 6, 46010 València, Valencia, España",
                39.4826729,
                -0.3639118999999482,
                ""
            ),
            CajeroEntity(
                5,
                "Av. del Regne de València, 2, 46005 València, Valencia, España",
                39.4647669,
                -0.3732760000000326,
                ""
            ))

        binding.listaCajeros.setOnClickListener {
            /*Thread{
                CajeroApplication.database.cajeroDao().insertAll(cajerosEntityLists)
            }.start()     aço s'ha quedat xapusa, enseñar a Anna*/

             intent = Intent(this, AtmListActivity::class.java)
            startActivity(intent)
        }
        binding.anadirCajeros.setOnClickListener {
            intent = Intent(this, AtmFormActivity::class.java)
            val editable = false
            intent.putExtra("afegir",editable)
            startActivity(intent)
        }
    }
}