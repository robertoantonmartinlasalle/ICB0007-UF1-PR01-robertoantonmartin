package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RocketListFragment : Fragment() {

    private lateinit var rocketListAdapter: RocketListAdapter
    private val rockets = mutableListOf<Rocket>()

    // Acceso al repositorio
    private lateinit var rocketRepository: RocketRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)

        // Inicializar el repositorio
        val database = AppDatabase.getDatabase(requireContext())
        rocketRepository = RocketRepository(database.rocketDao())

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        rocketListAdapter = RocketListAdapter(rockets) { rocket ->
            // Navegar al fragmento de detalles pasando el cohete seleccionado
            val action = RocketListFragmentDirections
                .actionRocketListFragmentToRocketDetailFragment(rocket)
            findNavController().navigate(action)
        }
        recyclerView.adapter = rocketListAdapter

        // Cargar los datos desde la base de datos o la API
        loadRockets()

        return view
    }

    private fun loadRockets() {
        lifecycleScope.launch {
            val localRockets = rocketRepository.getAllRockets()
            if (localRockets.isNotEmpty()) {
                rockets.clear()
                rockets.addAll(localRockets.map { it.toRocket() })
                rocketListAdapter.notifyDataSetChanged()
            } else {
                fetchRocketsFromApi()
            }
        }
    }

    private fun fetchRocketsFromApi() {
        val retrofit = RetrofitInstance.getRetrofitInstance()
        val service = retrofit.create(SpaceXApiService::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = service.getRockets()
                withContext(Dispatchers.Main) {
                    rockets.clear()
                    rockets.addAll(response)
                    rocketListAdapter.notifyDataSetChanged()
                    saveRocketsToLocalDatabase(response)
                }
            } catch (e: Exception) {
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

    private suspend fun saveRocketsToLocalDatabase(rockets: List<Rocket>) {
        val rocketEntities = rockets.map { it.toEntity() }
        rocketRepository.insertAllRockets(rocketEntities)
    }
}
