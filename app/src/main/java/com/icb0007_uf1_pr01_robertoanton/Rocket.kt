package com.icb0007_uf1_pr01_robertoanton

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Modelo principal de datos para representar un cohete.
 * Utilizado con Retrofit (para la API), Room (para almacenamiento local), y Parcelable (para navegación).
 */
@Parcelize
data class Rocket(
    @SerializedName("id") val id: String, // Identificador único del cohete en la API
    @SerializedName("name") val name: String, // Nombre del cohete
    @SerializedName("type") val type: String, // Tipo o categoría del cohete
    @SerializedName("active") val active: Boolean, // Indica si el cohete está actualmente en uso
    @SerializedName("stages") val stages: Int, // Número de etapas que componen el cohete
    @SerializedName("boosters") val boosters: Int, // Número de propulsores adicionales
    @SerializedName("cost_per_launch") val costPerLaunch: Long, // Costo promedio por lanzamiento en dólares
    @SerializedName("success_rate_pct") val successRate: Int, // Tasa de éxito en porcentaje
    @SerializedName("first_flight") val firstFlight: String, // Fecha del primer lanzamiento (formato ISO)
    @SerializedName("country") val country: String, // País de origen del cohete
    @SerializedName("company") val company: String, // Empresa responsable de la fabricación del cohete
    @SerializedName("description") val description: String, // Descripción detallada del cohete
    @SerializedName("wikipedia") val wikipedia: String, // Enlace a la página de Wikipedia del cohete
    @SerializedName("height") val height: Dimension, // Altura del cohete (en metros y pies)
    @SerializedName("diameter") val diameter: Dimension // Diámetro del cohete (en metros y pies)
) : Parcelable

/**
 * Modelo para las dimensiones físicas de un cohete (altura o diámetro).
 * Puede contener valores en metros y pies.
 */
@Parcelize
data class Dimension(
    @SerializedName("meters") val meters: Double?, // Dimensión en metros (puede ser nulo si no está disponible)
    @SerializedName("feet") val feet: Double? // Dimensión en pies (puede ser nulo si no está disponible)
) : Parcelable
