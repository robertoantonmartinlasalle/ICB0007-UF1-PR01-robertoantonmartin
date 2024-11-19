package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Fragmento que muestra una lista de cohetes utilizando un RecyclerView.
class RocketListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el diseño del fragmento desde el archivo XML (fragment_rocket_list.xml).
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)

        // Configura el RecyclerView para mostrar la lista de cohetes.
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRockets)

        // Asigna un administrador de diseño al RecyclerView.
        // En este caso, LinearLayoutManager organiza los elementos en una lista vertical.
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Asigna un adaptador al RecyclerView. Este adaptador conecta los datos con las vistas.
        recyclerView.adapter = RocketListAdapter(getSampleData())

        // Devuelve la vista inflada como el contenido del fragmento.
        return view
    }

    // Método privado para proporcionar datos de ejemplo.
    // Retorna una lista de nombres de cohetes que se mostrará en el RecyclerView.
    private fun getSampleData(): List<String> {
        return listOf("Falcon 1", "Falcon 9", "Falcon Heavy", "Starship")
    }
}
