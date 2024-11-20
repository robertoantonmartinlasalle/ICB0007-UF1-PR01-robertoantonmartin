package com.icb0007_uf1_pr01_robertoanton

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objeto para configurar Retrofit
object RetrofitInstance {
    // Base URL de la API de SpaceX
    private const val BASE_URL = "https://api.spacexdata.com/v4/"

    // Inicializamos Retrofit con el patrón Singleton
    val api: SpaceXApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Establece la URL base
            .addConverterFactory(GsonConverterFactory.create()) // Convierte JSON en objetos
            .build()
            .create(SpaceXApiService::class.java) // Crea la implementación de la interfaz
    }
}
