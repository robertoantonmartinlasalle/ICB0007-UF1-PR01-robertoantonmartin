package com.icb0007_uf1_pr01_robertoanton

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entidad para la base de datos Room
@Entity(tableName = "rockets")
data class RocketEntity(
    @PrimaryKey val id: String, // ID único del cohete
    val name: String, // Nombre del cohete
    val description: String, // Descripción del cohete
    val country: String, // País de origen
    val company: String, // Compañía fabricante
    val firstFlight: String, // Fecha del primer vuelo
    val successRate: Int, // Porcentaje de éxito
    val active: Boolean // Si el cohete está activo o no
)
