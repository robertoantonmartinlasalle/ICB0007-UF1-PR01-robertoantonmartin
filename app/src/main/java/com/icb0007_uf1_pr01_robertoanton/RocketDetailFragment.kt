package com.icb0007_uf1_pr01_robertoanton

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
import kotlinx.coroutines.launch

// Fragmento para mostrar los detalles de un cohete
class RocketDetailFragment : Fragment() {

    // Variable para almacenar el cohete actual
    private lateinit var rocket: Rocket

    // Repositorio para manejar operaciones relacionadas con la base de datos
    private lateinit var rocketRepository: RocketRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_rocket_detail, container, false)

        // Obtener el cohete desde los argumentos pasados al fragmento
        rocket = RocketDetailFragmentArgs.fromBundle(requireArguments()).rocket

        // Inicializar el repositorio con acceso a la base de datos
        val database = AppDatabase.getDatabase(requireContext())
        rocketRepository = RocketRepository(database.rocketDao())

        // Configurar las vistas con los datos del cohete
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
        view.findViewById<TextView>(R.id.textViewRocketCountry).text = "País: ${rocket.country}"
        view.findViewById<TextView>(R.id.textViewRocketCompany).text =
            "Compañía: ${rocket.company}"
        view.findViewById<TextView>(R.id.textViewRocketHeight).text =
            "Altura: ${rocket.height.meters ?: "N/A"} metros"
        view.findViewById<TextView>(R.id.textViewRocketDiameter).text =
            "Diámetro: ${rocket.diameter.meters ?: "N/A"} metros"

        // Configurar el botón "Editar" para navegar al fragmento de edición
        view.findViewById<Button>(R.id.buttonEditRocket).setOnClickListener {
            navigateToEditRocket()
        }

        // Configurar el botón "Eliminar" para eliminar el cohete actual
        view.findViewById<Button>(R.id.buttonDeleteRocket).setOnClickListener {
            deleteRocket()
        }

        return view
    }

    /**
     * Navegar al fragmento de edición del cohete
     */
    private fun navigateToEditRocket() {
        // Crear la acción para navegar al fragmento de edición con el cohete actual como argumento
        val action = RocketDetailFragmentDirections
            .actionRocketDetailFragmentToEditRocketFragment(rocket)
        findNavController().navigate(action) // Ejecutar la navegación
    }

    /**
     * Eliminar el cohete actual
     */
    private fun deleteRocket() {
        // Usar una coroutine para realizar la operación de eliminación en segundo plano
        lifecycleScope.launch {
            // Eliminar el cohete de la base de datos
            rocketRepository.deleteRocket(rocket.toEntity())
            // Mostrar un mensaje confirmando la eliminación
            Toast.makeText(requireContext(), "Cohete eliminado", Toast.LENGTH_SHORT).show()
            // Volver a la lista de cohetes
            findNavController().navigateUp()
        }
    }
}
