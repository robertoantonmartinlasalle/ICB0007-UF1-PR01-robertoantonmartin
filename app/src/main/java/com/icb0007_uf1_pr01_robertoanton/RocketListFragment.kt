package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RocketListFragment : Fragment() {

    // ViewModel para mantener la lista de cohetes
    private lateinit var viewModel: RocketViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)

        // Inicializar el ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(RocketViewModel::class.java)

        // Configurar el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewRockets)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Si los datos ya están en el ViewModel, configurarlos directamente
        if (viewModel.allRockets.isNotEmpty()) {
            updateRecyclerView(viewModel.allRockets)
        } else {
            // Si no hay datos, cargar los cohetes desde la API y RocketStorage
            loadRockets()
        }

        // Configurar el botón flotante para agregar un nuevo cohete
        val fabAddRocket = view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabAddRocket)
        fabAddRocket.setOnClickListener {
            val action = RocketListFragmentDirections.actionRocketListFragmentToCreateRocketFragment()
            findNavController().navigate(action)
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        // Obtener los cohetes actualizados desde RocketStorage
        val rocketsFromStorage = RocketStorage.getRockets()

        // Actualizar el ViewModel y el RecyclerView
        viewModel.allRockets = mergeLists(rocketsFromStorage, viewModel.allRockets)
        updateRecyclerView(viewModel.allRockets)
    }

    /**
     * Función para cargar los cohetes desde la API y RocketStorage.
     * Combina los cohetes obtenidos y los creados manualmente.
     */
    private fun loadRockets() {
        RetrofitInstance.api.getRockets().enqueue(object : Callback<List<Rocket>> {
            override fun onResponse(call: Call<List<Rocket>>, response: Response<List<Rocket>>) {
                val apiRockets = response.body() ?: emptyList()
                viewModel.allRockets = mergeLists(apiRockets, RocketStorage.getRockets())
                updateRecyclerView(viewModel.allRockets)
            }

            override fun onFailure(call: Call<List<Rocket>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    /**
     * Función para actualizar el RecyclerView con la lista de cohetes.
     * @param rockets Lista de cohetes a mostrar
     */
    private fun updateRecyclerView(rockets: List<Rocket>) {
        recyclerView.adapter = RocketAdapter(rockets) { rocket ->
            val action = RocketListFragmentDirections.actionRocketListFragmentToRocketDetailFragment(rocket)
            findNavController().navigate(action)
        }
    }

    /**
     * Combina dos listas de cohetes evitando duplicados y eliminados.
     * @param storageRockets Lista desde RocketStorage (estado actual)
     * @param viewModelRockets Lista desde el ViewModel (estado anterior)
     * @return Lista combinada actualizada
     */
    private fun mergeLists(storageRockets: List<Rocket>, viewModelRockets: List<Rocket>): List<Rocket> {
        // Crear un mapa basado en los nombres de los cohetes para evitar duplicados
        val rocketsMap = (viewModelRockets + storageRockets).associateBy { it.name }

        // Retornar los valores únicos como lista
        return rocketsMap.values.toList()
    }
}
