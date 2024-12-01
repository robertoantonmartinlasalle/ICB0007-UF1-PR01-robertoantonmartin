package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class RocketDetailFragment : Fragment() {

    private lateinit var rocket: Rocket

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_rocket_detail, container, false)

        // Obtener el cohete desde los argumentos
        rocket = RocketDetailFragmentArgs.fromBundle(requireArguments()).rocket

        // Configurar las vistas con los datos del cohete
        view.findViewById<TextView>(R.id.textViewRocketName).text = "Nombre: ${rocket.name}"
        view.findViewById<TextView>(R.id.textViewRocketType).text = "Tipo: ${rocket.type}"
        view.findViewById<TextView>(R.id.textViewRocketActive).text =
            "Activo: ${if (rocket.active) "Sí" else "No"}"
        view.findViewById<TextView>(R.id.textViewRocketStages).text = "Etapas: ${rocket.stages}"
        view.findViewById<TextView>(R.id.textViewRocketBoosters).text = "Propulsores: ${rocket.boosters}"
        view.findViewById<TextView>(R.id.textViewRocketCost).text = "Costo: ${rocket.costPerLaunch}"
        view.findViewById<TextView>(R.id.textViewRocketSuccessRate).text =
            "Éxito: ${rocket.successRate}%"
        view.findViewById<TextView>(R.id.textViewRocketFirstFlight).text =
            "Primer vuelo: ${rocket.firstFlight}"
        view.findViewById<TextView>(R.id.textViewRocketCountry).text = "País: ${rocket.country}"
        view.findViewById<TextView>(R.id.textViewRocketCompany).text = "Compañía: ${rocket.company}"
        view.findViewById<TextView>(R.id.textViewRocketHeight).text =
            "Altura: ${rocket.height.meters ?: "N/A"} metros"
        view.findViewById<TextView>(R.id.textViewRocketDiameter).text =
            "Diámetro: ${rocket.diameter.meters ?: "N/A"} metros"

        return view
    }
}
