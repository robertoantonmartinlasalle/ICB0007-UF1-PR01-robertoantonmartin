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
     */
    fun addRocket(name: String, description: String, type: String, country: String, company: String) {
        val newRocket = Rocket(
            name = name, // Nombre único del cohete
            type = type, // Tipo del cohete
            active = true, // Por defecto, los cohetes creados manualmente están activos
            cost_per_launch = 0, // Costo por lanzamiento (puedes agregarlo si es necesario)
            success_rate_pct = 0, // Porcentaje de éxito (valor predeterminado)
            country = country, // País de origen
            company = company, // Empresa fabricante
            wikipedia = "", // Enlace a Wikipedia (vacío para los creados manualmente)
            description = description, // Descripción proporcionada por el usuario
            height = Dimensions(0.0, 0.0), // Altura (valores predeterminados)
            diameter = Dimensions(0.0, 0.0) // Diámetro (valores predeterminados)
        )
        rockets.add(newRocket) // Agregar el nuevo cohete a la lista mutable
    }

    /**
     * Función para obtener todos los cohetes creados manualmente.
     * @return Lista de cohetes creados manualmente
     */
    fun getRockets(): List<Rocket> {
        return rockets // Retornar una copia inmutable de la lista
    }
}
