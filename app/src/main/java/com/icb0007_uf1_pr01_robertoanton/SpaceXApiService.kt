package com.icb0007_uf1_pr01_robertoanton

import retrofit2.http.GET
// Establecer la conexión con la API de SpaceX
interface SpaceXApiService {
    @GET("v4/rockets")
    suspend fun getRockets(): List<Rocket>
}
