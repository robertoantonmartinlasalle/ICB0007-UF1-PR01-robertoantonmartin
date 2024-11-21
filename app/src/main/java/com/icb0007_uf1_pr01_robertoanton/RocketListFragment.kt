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
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)

        // Configurar el RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRockets)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Llamada a la API para obtener la lista de cohetes
        RetrofitInstance.api.getRockets().enqueue(object : Callback<List<Rocket>> {
            override fun onResponse(call: Call<List<Rocket>>, response: Response<List<Rocket>>) {
                val rockets = response.body()
                if (rockets != null) {
                    recyclerView.adapter = RocketAdapter(rockets) { rocket ->
                        // Navegar al fragmento de detalles con el cohete seleccionado
                        val action =
                            RocketListFragmentDirections.actionRocketListFragmentToRocketDetailFragment(
                                rocket
                            )
                        findNavController().navigate(action)
                    }
                }
            }

            override fun onFailure(call: Call<List<Rocket>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return view
    }
}
