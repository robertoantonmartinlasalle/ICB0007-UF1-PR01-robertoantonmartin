package com.icb0007_uf1_pr01_robertoanton

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch

class RocketDetailFragment : Fragment() {

    private val args: RocketDetailFragmentArgs by navArgs()
    private lateinit var rocket: Rocket
    private lateinit var rocketRepository: RocketRepository

    // Variable para controlar si se muestran los botones
    private var showButtons: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_rocket_detail, container, false)

        rocket = args.rocket
        showButtons = args.showButtons // Obtener el argumento showButtons

        val database = AppDatabase.getDatabase(requireContext())
        rocketRepository = RocketRepository(database.rocketDao())

        configureViews(view)
        configureButtons(view)

        return view
    }

    private fun configureViews(view: View) {
        view.findViewById<TextView>(R.id.textViewRocketName).text = "Nombre: ${rocket.name}"
        view.findViewById<TextView>(R.id.textViewRocketType).text = "Tipo: ${rocket.type}"
        view.findViewById<TextView>(R.id.textViewRocketActive).text =
            "Activo: ${if (rocket.active) "Sí" else "No"}"
        view.findViewById<TextView>(R.id.textViewRocketStages).text = "Etapas: ${rocket.stages}"
        view.findViewById<TextView>(R.id.textViewRocketBoosters).text =
            "Propulsores: ${rocket.boosters}"
        view.findViewById<TextView>(R.id.textViewRocketCost).text =
            "Costo: ${rocket.costPerLaunch}"
        view.findViewById<TextView>(R.id.textViewRocketSuccessRate).text =
            "Éxito: ${rocket.successRate}%"
        view.findViewById<TextView>(R.id.textViewRocketFirstFlight).text =
            "Primer vuelo: ${rocket.firstFlight}"

        // Restaurar funcionalidad de clic en el país
        view.findViewById<TextView>(R.id.textViewRocketCountry).apply {
            text = "País: ${rocket.country}"
            setTextColor(resources.getColor(R.color.link_color, null)) // Estilo de enlace
            setOnClickListener {
                openCountryInMaps(rocket.country) // Llamar a la función para abrir Google Maps
            }
        }

        view.findViewById<TextView>(R.id.textViewRocketCompany).text =
            "Compañía: ${rocket.company}"
        view.findViewById<TextView>(R.id.textViewRocketHeight).text =
            "Altura: ${rocket.height.meters ?: "N/A"} metros"
        view.findViewById<TextView>(R.id.textViewRocketDiameter).text =
            "Diámetro: ${rocket.diameter.meters ?: "N/A"} metros"
    }

    private fun configureButtons(view: View) {
        val buttonEdit = view.findViewById<Button>(R.id.buttonEditRocket)
        val buttonDelete = view.findViewById<Button>(R.id.buttonDeleteRocket)

        if (showButtons) {
            // Configurar botones si deben mostrarse
            buttonEdit.setOnClickListener { navigateToEditRocket() }
            buttonDelete.setOnClickListener { deleteRocket() }
        } else {
            // Ocultar botones si showButtons es false
            buttonEdit.visibility = View.GONE
            buttonDelete.visibility = View.GONE
        }
    }

    private fun navigateToEditRocket() {
        val action = RocketDetailFragmentDirections
            .actionRocketDetailFragmentToEditRocketFragment(rocket)
        findNavController().navigate(action)
    }

    private fun deleteRocket() {
        lifecycleScope.launch {
            rocketRepository.deleteRocket(rocket.toEntity())
            Toast.makeText(requireContext(), "Cohete eliminado", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }
    /* Para adecuar a la tarea tarea de mostrar la ubicación del cohete en el maps establezco la
    función openCountryInMaps
    */
    /**
     * Abrir el país en Google Maps.
     */
    private fun openCountryInMaps(country: String) {
        try {
            val geoUri = Uri.parse("geo:0,0?q=$country") // Crear URI para la búsqueda en Maps
            val intent = Intent(Intent.ACTION_VIEW, geoUri).apply {
                setPackage("com.google.android.apps.maps") // Asegurar que se abra con Google Maps
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "No se pudo abrir Google Maps. Asegúrate de tenerlo instalado.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
