package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/") // Base URL de la API
            .addConverterFactory(GsonConverterFactory.create()) // Convertidor JSON
            .build()

        // Crear el servicio de la API
        val service = retrofit.create(SpaceXApiService::class.java)

        // Llamar a la API en un Coroutine
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val rockets = service.getRockets() // Realizar la petición
                rockets.forEach { rocket ->
                    Log.d("SpaceXAPI", """
                        Nombre: ${rocket.name}
                        Tipo: ${rocket.type}
                        Activo: ${rocket.active}
                        Etapas: ${rocket.stages}
                        Propulsores: ${rocket.boosters}
                        Costo por lanzamiento: ${rocket.costPerLaunch}
                        Porcentaje de éxito: ${rocket.successRate}%
                        Primer vuelo: ${rocket.firstFlight}
                        País: ${rocket.country}
                        Compañía: ${rocket.company}
                        Descripción: ${rocket.description}
                        Wikipedia: ${rocket.wikipedia}
                        Altura: ${rocket.height.meters} m / ${rocket.height.feet} ft
                        Diámetro: ${rocket.diameter.meters} m / ${rocket.diameter.feet} ft
                    """.trimIndent())
                }
            } catch (e: Exception) {
                Log.e("SpaceXAPI", "Error al obtener los cohetes", e)
            }
        }
    }
}
