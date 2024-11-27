package com.icb0007_uf1_pr01_robertoanton

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Modelo de datos para un cohete
@Parcelize
data class Rocket(
    var name: String, // Cambiado de val a var para permitir modificaciones
    var type: String, // Cambiado de val a var para permitir modificaciones si lo deseas
    var active: Boolean, // Cambiado de val a var para permitir modificaciones si lo deseas
    var cost_per_launch: Int, // Cambiado de val a var para permitir modificaciones si lo deseas
    var success_rate_pct: Int, // Cambiado de val a var para permitir modificaciones si lo deseas
    var country: String, // Cambiado de val a var para permitir modificaciones si lo deseas
    var company: String, // Cambiado de val a var para permitir modificaciones si lo deseas
    var wikipedia: String, // Cambiado de val a var para permitir modificaciones si lo deseas
    var description: String, // Cambiado de val a var para permitir modificaciones
    var height: Dimensions, // Cambiado de val a var para permitir modificaciones si lo deseas
    var diameter: Dimensions // Cambiado de val a var para permitir modificaciones si lo deseas
) : Parcelable

// Modelo de dimensiones (altura y diámetro)
@Parcelize
data class Dimensions(
    var meters: Double, // Cambiado de val a var para permitir modificaciones
    var feet: Double // Cambiado de val a var para permitir modificaciones
) : Parcelable
