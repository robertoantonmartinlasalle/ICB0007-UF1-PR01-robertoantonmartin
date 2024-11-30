package com.icb0007_uf1_pr01_robertoanton

// Extensión para convertir Rocket a RocketEntity
fun Rocket.toEntity(): RocketEntity {
    return RocketEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        country = this.country,
        company = this.company,
        firstFlight = this.firstFlight,
        successRate = this.successRate,
        active = this.active
    )
}

// Extensión para convertir RocketEntity a Rocket
fun RocketEntity.toRocket(): Rocket {
    return Rocket(
        id = this.id,
        name = this.name,
        type = "", // Campo no disponible en la entidad
        active = this.active,
        stages = 0, // Campo no disponible en la entidad
        boosters = 0, // Campo no disponible en la entidad
        costPerLaunch = 0L, // Campo no disponible en la entidad
        successRate = this.successRate,
        firstFlight = this.firstFlight,
        country = this.country,
        company = this.company,
        description = this.description,
        wikipedia = "", // Campo no disponible en la entidad
        height = Dimension(null, null), // Dimensiones no disponibles en la entidad
        diameter = Dimension(null, null) // Dimensiones no disponibles en la entidad
    )
}
