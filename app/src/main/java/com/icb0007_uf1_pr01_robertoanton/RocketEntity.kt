    package com.icb0007_uf1_pr01_robertoanton

    import androidx.room.Entity
    import androidx.room.PrimaryKey

    // Es necesario definir una entidad para Room
    @Entity(tableName = "rockets")
    data class RocketEntity(
        @PrimaryKey val id: String,
        val name: String,
        val type: String,
        val description: String,
        val country: String,
        val company: String,
        val firstFlight: String,
        val successRate: Int,
        val active: Boolean,
        val stages: Int,
        val boosters: Int,
        val costPerLaunch: Long,
        val wikipedia: String,
        val heightMeters: Double?,
        val heightFeet: Double?,
        val diameterMeters: Double?,
        val diameterFeet: Double?
    )

