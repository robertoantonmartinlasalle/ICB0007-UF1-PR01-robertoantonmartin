package com.icb0007_uf1_pr01_robertoanton

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// DAO (Data Access Object) para interactuar con la tabla "rockets" de la base de datos
@Dao
interface RocketDao {

    // Insertar una lista de cohetes en la base de datos.
    // Si un cohete con el mismo ID ya existe, se reemplaza.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rockets: List<RocketEntity>)

    // Insertar un único cohete en la base de datos.
    // Si un cohete con el mismo ID ya existe, se reemplaza.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRocket(rocket: RocketEntity)

    // Obtener todos los cohetes almacenados en la base de datos.
    // Retorna una lista de todos los registros en la tabla "rockets".
    @Query("SELECT * FROM rockets")
    suspend fun getAllRockets(): List<RocketEntity>

    // Limpiar la tabla "rockets", eliminando todos los registros existentes.
    @Query("DELETE FROM rockets")
    suspend fun clearAll()

    // Eliminar un cohete específico de la base de datos.
    @Delete
    suspend fun delete(rocket: RocketEntity)
}
