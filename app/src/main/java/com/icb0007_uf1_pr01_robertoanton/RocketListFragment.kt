package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RocketListFragment : Fragment() {

    // Declaración del adaptador y lista de cohetes
    private lateinit var rocketListAdapter: RocketListAdapter
    private val rockets = mutableListOf<Rocket>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout del fragmento
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Diseño en lista
        rocketListAdapter = RocketListAdapter(rockets) // Inicializar adaptador con la lista
        recyclerView.adapter = rocketListAdapter

        // Llamar a la API para obtener los datos
        fetchRockets()

        return view
    }

    private fun fetchRockets() {
        // Configuración de Retrofit para realizar la llamada a la API
        val retrofit = RetrofitInstance.getRetrofitInstance()
        val service = retrofit.create(SpaceXApiService::class.java)

        // Usar corrutinas para realizar la llamada en un hilo de fondo
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getRockets() // Obtener cohetes desde la API
                withContext(Dispatchers.Main) {
                    rockets.clear() // Limpiar la lista anterior
                    rockets.addAll(response) // Añadir los nuevos cohetes
                    rocketListAdapter.notifyDataSetChanged() // Actualizar RecyclerView
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Mostrar mensaje de error
                    Toast.makeText(
                        requireContext(),
                        "Error al obtener los cohetes: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
