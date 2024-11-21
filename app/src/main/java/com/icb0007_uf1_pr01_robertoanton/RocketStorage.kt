package com.icb0007_uf1_pr01_robertoanton

object RocketStorage {
    private val rockets = mutableListOf<Rocket>()

    // Agregar un nuevo cohete
    fun addRocket(name: String, description: String) {
        val newRocket = Rocket(
            name = name,
            type = "Custom",
            active = true,
            cost_per_launch = 0,
            success_rate_pct = 0,
            country = "Desconocido",
            company = "Personalizado",
            wikipedia = "",
            description = description,
            height = Dimensions(0.0, 0.0),
            diameter = Dimensions(0.0, 0.0)
        )
        rockets.add(newRocket)
    }

    // Obtener todos los cohetes
    fun getRockets(): List<Rocket> {
        return rockets
    }
}
