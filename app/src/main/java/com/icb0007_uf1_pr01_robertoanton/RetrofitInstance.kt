package com.icb0007_uf1_pr01_robertoanton

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objeto que proporciona una instancia de Retrofit configurada
object RetrofitInstance {

    // URL base para las solicitudes de la API de SpaceX
    private const val BASE_URL = "https://api.spacexdata.com/"

    /**
     * Método para obtener una instancia de Retrofit configurada.
     * @return Una instancia de Retrofit lista para usar con la API.
     */
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            // Establece la URL base para las solicitudes de red
            .baseUrl(BASE_URL)
            // Agrega un convertidor para transformar JSON a objetos Kotlin y viceversa.
            .addConverterFactory(GsonConverterFactory.create())
            // Crea y retorna la instancia de Retrofit
            .build()
    }
}
