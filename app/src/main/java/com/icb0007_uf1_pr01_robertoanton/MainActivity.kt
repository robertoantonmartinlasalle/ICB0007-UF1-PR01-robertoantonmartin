package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var fabAddRocket: FloatingActionButton // Referencia al botón flotante

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar Retrofit para interactuar con la API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/") // Base URL de la API de SpaceX
            .addConverterFactory(GsonConverterFactory.create()) // Convertidor JSON
            .build()

        // Crear el servicio de la API
        val service = retrofit.create(SpaceXApiService::class.java)

        // Llamar a la API en un Coroutine (fuera del hilo principal)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Realizar la petición para obtener la lista de cohetes
                val rockets = service.getRockets()

                // Iterar por cada cohete obtenido y mostrar los datos en los logs
                rockets.forEach { rocket ->
                    Log.d(
                        "SpaceXAPI", """
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
                    """.trimIndent()
                    )
                }
            } catch (e: Exception) {
                // Manejar cualquier error en la llamada a la API
                Log.e("SpaceXAPI", "Error al obtener los cohetes", e)
            }
        }

        // Configurar el Floating Action Button (FAB)
        fabAddRocket = findViewById(R.id.fab_add_rocket)

        // Controlar la visibilidad del FAB según el fragmento activo
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.rocketListFragment -> fabAddRocket.show() // Mostrar solo en la lista de cohetes
                else -> fabAddRocket.hide() // Ocultar en otros fragmentos
            }
        }

        // Configurar la acción al pulsar el FAB
        fabAddRocket.setOnClickListener {
            navController.navigate(R.id.action_rocketListFragment_to_addRocketFragment)
        }
    }
}
