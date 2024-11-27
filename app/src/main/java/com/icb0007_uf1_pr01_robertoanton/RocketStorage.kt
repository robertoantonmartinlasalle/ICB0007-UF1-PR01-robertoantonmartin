package com.icb0007_uf1_pr01_robertoanton

// Objeto para manejar el almacenamiento temporal de los cohetes creados
object RocketStorage {
    // Lista mutable para almacenar los cohetes
    private val rockets = mutableListOf<Rocket>()

    /**
     * Función para agregar un nuevo cohete a la lista.
     * @param name Nombre del cohete
     * @param description Descripción del cohete
     * @param type Tipo del cohete
     * @param country País de origen del cohete
     * @param company Empresa que fabrica el cohete
     * @param cost_per_launch Costo por lanzamiento
     * @param success_rate_pct Porcentaje de éxito
     * @param height Altura en metros
     * @param diameter Diámetro en metros
     */
    fun addRocket(
        name: String,
        description: String,
        type: String,
        country: String,
        company: String,
        cost_per_launch: Int,
        success_rate_pct: Int,
        height: Double,
        diameter: Double
    ) {
        val newRocket = Rocket(
            name = name,
            type = type,
            active = true,
            cost_per_launch = cost_per_launch,
            success_rate_pct = success_rate_pct,
            country = country,
            company = company,
            wikipedia = "",
            description = description,
            height = Dimensions(height, height * 3.28084),
            diameter = Dimensions(diameter, diameter * 3.28084)
        )
        rockets.add(newRocket)
    }

    /**
     * Función para obtener todos los cohetes creados manualmente.
     * @return Lista de cohetes creados manualmente
     */
    fun getRockets(): List<Rocket> {
        return rockets
    }
}
