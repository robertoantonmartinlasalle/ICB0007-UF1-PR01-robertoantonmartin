package com.icb0007_uf1_pr01_robertoanton

import retrofit2.Call
import retrofit2.http.GET

// Interfaz para definir los endpoints de la API
interface SpaceXApiService {
    // Endpoint para obtener la lista de cohetes
    @GET("rockets")
    fun getRockets(): Call<List<Rocket>>
}
