package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class RocketDetailFragment : Fragment() {

    private val args: RocketDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rocket_detail, container, false)

        val rocket = args.rocket

        // Referencias a los elementos de la UI
        view.findViewById<TextView>(R.id.textViewRocketDetailName).text = rocket.name
        view.findViewById<TextView>(R.id.textViewRocketType).text = "Type: ${rocket.type}"
        view.findViewById<TextView>(R.id.textViewRocketCountry).text = "Country: ${rocket.country}"
        view.findViewById<TextView>(R.id.textViewRocketCompany).text = "Company: ${rocket.company}"
        view.findViewById<TextView>(R.id.textViewRocketSuccessRate).text =
            "Success Rate: ${rocket.success_rate_pct}%"
        view.findViewById<TextView>(R.id.textViewRocketCost).text =
            "Cost per launch: $${rocket.cost_per_launch}"
        view.findViewById<TextView>(R.id.textViewRocketHeight).text =
            "Height: ${rocket.height.meters} m / ${rocket.height.feet} ft"
        view.findViewById<TextView>(R.id.textViewRocketDiameter).text =
            "Diameter: ${rocket.diameter.meters} m / ${rocket.diameter.feet} ft"

        // Botón para editar el cohete
        val editButton = view.findViewById<Button>(R.id.btnEditRocket)
        editButton.setOnClickListener {
            // Navegar al fragmento de edición con los datos actuales del cohete
            val action = RocketDetailFragmentDirections.actionRocketDetailFragmentToEditRocketFragment(rocket)
            findNavController().navigate(action)
        }

        // Botón para eliminar el cohete
        val deleteButton = view.findViewById<Button>(R.id.btnDeleteRocket)
        deleteButton.setOnClickListener {
            // Eliminar el cohete del almacenamiento
            RocketStorage.deleteRocket(rocket.name)
            Toast.makeText(requireContext(), "Cohete eliminado", Toast.LENGTH_SHORT).show()

            // Navegar de regreso a la lista
            findNavController().navigateUp()
        }

        return view
    }
}
