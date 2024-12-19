package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

// Fragmento para editar un cohete ya existente
class EditRocketFragment : Fragment() {

    // Instancia del cohete que se editará
    private lateinit var rocket: Rocket

    // Repositorio para manejar operaciones con la base de datos
    private lateinit var rocketRepository: RocketRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el diseño del fragmento de edición
        val view = inflater.inflate(R.layout.fragment_edit_rocket, container, false)

        // Obtener el cohete desde los argumentos pasados al fragmento
        rocket = EditRocketFragmentArgs.fromBundle(requireArguments()).rocket

        // Inicializar el repositorio para interactuar con la base de datos
        val database = AppDatabase.getDatabase(requireContext())
        rocketRepository = RocketRepository(database.rocketDao())

        // Configuración de las vistas
        val editTextName = view.findViewById<EditText>(R.id.editTextRocketName)
        val editTextType = view.findViewById<EditText>(R.id.editTextRocketType)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextRocketDescription)
        val checkBoxActive = view.findViewById<CheckBox>(R.id.checkBoxRocketActive)
        val editTextStages = view.findViewById<EditText>(R.id.editTextRocketStages)
        val editTextBoosters = view.findViewById<EditText>(R.id.editTextRocketBoosters)
        val editTextCost = view.findViewById<EditText>(R.id.editTextRocketCost)
        val editTextHeight = view.findViewById<EditText>(R.id.editTextRocketHeight)
        val editTextDiameter = view.findViewById<EditText>(R.id.editTextRocketDiameter)
        val editTextSuccessRate = view.findViewById<EditText>(R.id.editTextRocketSuccessRate)
        val editTextFirstFlight = view.findViewById<EditText>(R.id.editTextRocketFirstFlight)
        val editTextCountry = view.findViewById<EditText>(R.id.editTextRocketCountry)
        val editTextCompany = view.findViewById<EditText>(R.id.editTextRocketCompany)

        // Para cargar los datos actuales del cohete en las vistas del formulario
        editTextName.setText(rocket.name)
        editTextType.setText(rocket.type)
        editTextDescription.setText(rocket.description)
        checkBoxActive.isChecked = rocket.active
        editTextStages.setText(rocket.stages.toString())
        editTextBoosters.setText(rocket.boosters.toString())
        editTextCost.setText(rocket.costPerLaunch.toString())
        editTextHeight.setText(rocket.height.meters?.toString() ?: "")
        editTextDiameter.setText(rocket.diameter.meters?.toString() ?: "")
        editTextSuccessRate.setText(rocket.successRate.toString())
        editTextFirstFlight.setText(rocket.firstFlight)
        editTextCountry.setText(rocket.country)
        editTextCompany.setText(rocket.company)

        // Configuración  del botón para "Guardar cambios"
        view.findViewById<Button>(R.id.buttonSaveRocket).setOnClickListener {
            saveRocket(
                editTextName.text.toString(),
                editTextType.text.toString(),
                editTextDescription.text.toString(),
                checkBoxActive.isChecked,
                editTextStages.text.toString().toIntOrNull(),
                editTextBoosters.text.toString().toIntOrNull(),
                editTextCost.text.toString().toLongOrNull(),
                editTextHeight.text.toString().toDoubleOrNull(),
                editTextDiameter.text.toString().toDoubleOrNull(),
                editTextSuccessRate.text.toString().toIntOrNull(),
                editTextFirstFlight.text.toString(),
                editTextCountry.text.toString(),
                editTextCompany.text.toString()
            )
        }

        return view
    }

    /**
     * Método para guardar los cambios realizados en el cohete.
     */
    private fun saveRocket(
        name: String,
        type: String,
        description: String,
        active: Boolean,
        stages: Int?,
        boosters: Int?,
        costPerLaunch: Long?,
        height: Double?,
        diameter: Double?,
        successRate: Int?,
        firstFlight: String?,
        country: String?,
        company: String?
    ) {
        // Validar que todos los campos sean correctos y no nulos
        if (name.isBlank() || type.isBlank() || description.isBlank() || stages == null ||
            boosters == null || costPerLaunch == null || height == null || diameter == null ||
            successRate == null || firstFlight.isNullOrBlank() || country.isNullOrBlank() ||
            company.isNullOrBlank()
        ) {
            Toast.makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        // Para actualizar los datos del cohete
        rocket = rocket.copy(
            name = name,
            type = type,
            description = description,
            active = active,
            stages = stages,
            boosters = boosters,
            costPerLaunch = costPerLaunch,
            height = Dimension(meters = height, feet = height * 3.28084), // Conversión a pies
            diameter = Dimension(meters = diameter, feet = diameter * 3.28084), // Conversión a pies
            successRate = successRate,
            firstFlight = firstFlight,
            country = country,
            company = company
        )

        // Guardar los cambios en la base de datos
        lifecycleScope.launch {
            rocketRepository.insertRocket(rocket.toEntity())

            // Se navega de vuelta al listado de cohetes
            try {
                findNavController().navigate(R.id.action_editRocketFragment_to_rocketListFragment)
                Toast.makeText(requireContext(), "Cohete actualizado con éxito", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error al navegar: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
