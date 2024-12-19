package com.icb0007_uf1_pr01_robertoanton
// Actua de conversor de datos entre Rocket y RocketEntity
// Extensión para convertir Rocket a RocketEntity
fun Rocket.toEntity(): RocketEntity {
    return RocketEntity(
        id = this.id,
        name = this.name,
        type = this.type,
        description = this.description,
        country = this.country,
        company = this.company,
        firstFlight = this.firstFlight,
        successRate = this.successRate,
        active = this.active,
        stages = this.stages,
        boosters = this.boosters,
        costPerLaunch = this.costPerLaunch,
        wikipedia = this.wikipedia,
        heightMeters = this.height.meters,
        heightFeet = this.height.feet,
        diameterMeters = this.diameter.meters,
        diameterFeet = this.diameter.feet
    )
}

// Extensión para convertir RocketEntity a Rocket
fun RocketEntity.toRocket(): Rocket {
    return Rocket(
        id = this.id,
        name = this.name,
        type = this.type,
        active = this.active,
        stages = this.stages,
        boosters = this.boosters,
        costPerLaunch = this.costPerLaunch,
        successRate = this.successRate,
        firstFlight = this.firstFlight,
        country = this.country,
        company = this.company,
        description = this.description,
        wikipedia = this.wikipedia,
        height = Dimension(this.heightMeters, this.heightFeet),
        diameter = Dimension(this.diameterMeters, this.diameterFeet)
    )
}
