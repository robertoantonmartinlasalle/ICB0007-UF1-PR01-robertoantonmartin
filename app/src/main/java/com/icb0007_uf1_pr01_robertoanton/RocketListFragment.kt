package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RocketListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento desde el archivo XML correspondiente
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)

        // Configurar el RecyclerView para mostrar la lista de cohetes
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRockets)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Configurar diseño en lista

        // Llamar a la API de SpaceX para obtener los cohetes
        RetrofitInstance.api.getRockets().enqueue(object : Callback<List<Rocket>> {
            override fun onResponse(call: Call<List<Rocket>>, response: Response<List<Rocket>>) {
                val apiRockets = response.body() ?: emptyList() // Cohetes obtenidos de la API

                // Combinar los cohetes de la API con los creados manualmente
                val allRockets = apiRockets + RocketStorage.getRockets()

                // Configurar el adaptador del RecyclerView con la lista combinada
                recyclerView.adapter = RocketAdapter(allRockets) { rocket ->
                    // Acción al hacer clic en un cohete: navegar al fragmento de detalles
                    val action = RocketListFragmentDirections.actionRocketListFragmentToRocketDetailFragment(rocket)
                    findNavController().navigate(action)
                }
            }

            override fun onFailure(call: Call<List<Rocket>>, t: Throwable) {
                // Manejar fallos en la llamada a la API (por ejemplo, problemas de conexión)
                t.printStackTrace()
            }
        })

        // Configurar el botón flotante para agregar un nuevo cohete
        val fabAddRocket = view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabAddRocket)
        fabAddRocket.setOnClickListener {
            // Navegar al fragmento de creación de cohetes
            val action = RocketListFragmentDirections.actionRocketListFragmentToCreateRocketFragment()
            findNavController().navigate(action)
        }

        return view // Retornar la vista inflada
    }

    override fun onResume() {
        super.onResume()

        // Actualizar la lista de cohetes desde RocketStorage cada vez que se reanuda el fragmento
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewRockets)
        val allRockets = RocketStorage.getRockets() // Obtener todos los cohetes creados manualmente
        recyclerView?.adapter = RocketAdapter(allRockets) { rocket ->
            val action = RocketListFragmentDirections.actionRocketListFragmentToRocketDetailFragment(rocket)
            findNavController().navigate(action)
        }
    }
}
