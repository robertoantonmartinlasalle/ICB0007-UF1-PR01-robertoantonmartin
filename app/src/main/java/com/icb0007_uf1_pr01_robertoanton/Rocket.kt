package com.icb0007_uf1_pr01_robertoanton

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Modelo de datos para un cohete
@Parcelize
data class Rocket(
    val name: String, // Nombre del cohete
    val type: String, // Tipo de cohete
    val active: Boolean, // Estado de actividad
    val cost_per_launch: Int, // Costo por lanzamiento
    val success_rate_pct: Int, // Porcentaje de éxito
    val country: String, // País
    val company: String, // Empresa que lo fabrica
    val wikipedia: String, // Enlace a Wikipedia
    val description: String, // Descripción del cohete
    val height: Dimensions, // Altura del cohete
    val diameter: Dimensions // Diámetro del cohete
) : Parcelable

// Modelo de dimensiones (altura y diámetro)
@Parcelize
data class Dimensions(
    val meters: Double, // Medidas en metros
    val feet: Double // Medidas en pies
) : Parcelable
