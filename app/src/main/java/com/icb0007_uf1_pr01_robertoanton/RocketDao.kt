package com.icb0007_uf1_pr01_robertoanton

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// DAO para interactuar con la tabla de cohetes
@Dao
interface RocketDao {

    // Insertar una lista de cohetes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rockets: List<RocketEntity>)

    // Obtener todos los cohetes guardados
    @Query("SELECT * FROM rockets")
    suspend fun getAllRockets(): List<RocketEntity>

    // Limpiar todos los cohetes
    @Query("DELETE FROM rockets")
    suspend fun clearAll()
}
