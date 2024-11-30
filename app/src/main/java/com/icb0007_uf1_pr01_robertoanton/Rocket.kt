package com.icb0007_uf1_pr01_robertoanton

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// Modelo principal utilizado para Retrofit, Room y navegación
@Parcelize
data class Rocket(
    @SerializedName("id") val id: String, // ID único del cohete
    @SerializedName("name") val name: String, // Nombre del cohete
    @SerializedName("type") val type: String, // Tipo de cohete
    @SerializedName("active") val active: Boolean, // Si está activo
    @SerializedName("stages") val stages: Int, // Número de etapas
    @SerializedName("boosters") val boosters: Int, // Número de propulsores
    @SerializedName("cost_per_launch") val costPerLaunch: Long, // Costo por lanzamiento
    @SerializedName("success_rate_pct") val successRate: Int, // Porcentaje de éxito
    @SerializedName("first_flight") val firstFlight: String, // Fecha del primer vuelo
    @SerializedName("country") val country: String, // País de origen
    @SerializedName("company") val company: String, // Compañía fabricante
    @SerializedName("description") val description: String, // Descripción
    @SerializedName("wikipedia") val wikipedia: String, // URL de Wikipedia
    @SerializedName("height") val height: Dimension, // Altura del cohete
    @SerializedName("diameter") val diameter: Dimension // Diámetro del cohete
) : Parcelable

@Parcelize
data class Dimension(
    @SerializedName("meters") val meters: Double?, // Dimensión en metros
    @SerializedName("feet") val feet: Double? // Dimensión en pies
) : Parcelable
