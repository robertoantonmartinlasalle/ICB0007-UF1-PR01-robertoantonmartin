package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Fragmento para mostrar la lista de cohetes.
 * Este fragmento utiliza Room para datos locales y Retrofit para datos remotos.
 */
class RocketListFragment : Fragment() {

    // Adaptador para el RecyclerView y lista mutable de cohetes
    private lateinit var rocketListAdapter: RocketListAdapter
    private val rockets = mutableListOf<Rocket>()

    // Repositorio para manejar los datos (Room)
    private lateinit var rocketRepository: RocketRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)

        // Inicializar la base de datos y el repositorio
        val database = AppDatabase.getDatabase(requireContext())
        rocketRepository = RocketRepository(database.rocketDao())

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Diseño de lista vertical
        rocketListAdapter = RocketListAdapter(rockets) // Adaptador para la lista
        recyclerView.adapter = rocketListAdapter

        // Cargar los datos (desde Room o API)
        loadRockets()

        return view
    }

    /**
     * Cargar los datos de los cohetes.
     * Si hay datos locales, se usan; de lo contrario, se obtienen de la API.
     */
    private fun loadRockets() {
        lifecycleScope.launch {
            // Intentar cargar datos desde la base de datos local
            val localRockets = rocketRepository.getAllRockets()
            if (localRockets.isNotEmpty()) {
                // Mostrar los datos locales si existen
                rockets.clear()
                rockets.addAll(localRockets.map { it.toRocket() }) // Convertir entidades a objetos
                rocketListAdapter.notifyDataSetChanged()
            } else {
                // Si no hay datos locales, obtenerlos desde la API
                fetchRocketsFromApi()
            }
        }
    }

    /**
     * Realizar una llamada a la API para obtener datos de cohetes.
     */
    private fun fetchRocketsFromApi() {
        val retrofit = RetrofitInstance.getRetrofitInstance()
        val service = retrofit.create(SpaceXApiService::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Llamada a la API para obtener los cohetes
                val response = service.getRockets()
                withContext(Dispatchers.Main) {
                    // Actualizar la lista de cohetes en la interfaz
                    rockets.clear()
                    rockets.addAll(response)
                    rocketListAdapter.notifyDataSetChanged()

                    // Guardar los datos obtenidos en la base de datos local
                    saveRocketsToLocalDatabase(response)
                }
            } catch (e: Exception) {
                // Mostrar un mensaje de error si la API falla
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "Error al obtener los cohetes: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    /**
     * Guardar los datos obtenidos de la API en la base de datos local (Room).
     * @param rockets Lista de cohetes obtenida de la API.
     */
    private suspend fun saveRocketsToLocalDatabase(rockets: List<Rocket>) {
        val rocketEntities = rockets.map { it.toEntity() } // Convertir a entidades de Room
        rocketRepository.insertAllRockets(rocketEntities) // Insertar en la base de datos
    }
}
