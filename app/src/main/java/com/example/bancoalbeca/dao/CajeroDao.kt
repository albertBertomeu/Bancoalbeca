package com.example.bancoalbeca.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bancoalbeca.entities.CajeroEntity
@Dao
interface CajeroDao {
    @Query("SELECT * FROM CajeroEntity")
    fun getAllCajeros() : MutableList<CajeroEntity>

    @Query("SELECT *FROM CajeroEntity WHERE id=:id")
    fun getCajerosById(id:Long?) : CajeroEntity
    @Insert
    fun insertAll(cajeroEntityList : List<CajeroEntity>)
    @Insert
    fun addCajero(cajeroEntity: CajeroEntity)
    @Update
    fun updateCajero(cajeroEntity: CajeroEntity)
    @Delete
    fun deleteCajero(cajeroEntity: CajeroEntity)
}