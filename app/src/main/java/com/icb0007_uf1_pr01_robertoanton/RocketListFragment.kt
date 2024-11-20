package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Fragmento para mostrar la lista de cohetes
class RocketListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)

        // Configurar el RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRockets)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Configurar la lista como vertical
        recyclerView.adapter = RocketListAdapter(getSampleData()) { rocketName ->
            // Acción al hacer clic en un elemento de la lista (navegación usando SafeArgs)
            val action = RocketListFragmentDirections
                .actionRocketListFragmentToRocketDetailFragment(rocketName) // Crear la acción con SafeArgs
            findNavController().navigate(action) // Navegar al fragmento de detalles
        }

        return view
    }

    // Método para generar datos de ejemplo
    private fun getSampleData(): List<String> {
        return listOf("Falcon 1", "Falcon 9", "Falcon Heavy", "Starship") // Cohetes de ejemplo
    }
}
