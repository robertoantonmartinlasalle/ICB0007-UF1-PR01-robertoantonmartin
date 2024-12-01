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

// Fragmento para editar un cohete existente
class EditRocketFragment : Fragment() {

    private lateinit var rocket: Rocket
    private lateinit var rocketRepository: RocketRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_edit_rocket, container, false)

        // Obtener el cohete desde los argumentos
        rocket = EditRocketFragmentArgs.fromBundle(requireArguments()).rocket

        // Inicializar el repositorio para interactuar con la base de datos
        val database = AppDatabase.getDatabase(requireContext())
        rocketRepository = RocketRepository(database.rocketDao())

        // Configurar vistas
        val editTextName = view.findViewById<EditText>(R.id.editTextRocketName)
        val editTextType = view.findViewById<EditText>(R.id.editTextRocketType)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextRocketDescription)
        val checkBoxActive = view.findViewById<CheckBox>(R.id.checkBoxRocketActive)
        val editTextStages = view.findViewById<EditText>(R.id.editTextRocketStages)
        val editTextBoosters = view.findViewById<EditText>(R.id.editTextRocketBoosters)
        val editTextCost = view.findViewById<EditText>(R.id.editTextRocketCost)

        // Cargar datos del cohete en las vistas
        editTextName.setText(rocket.name)
        editTextType.setText(rocket.type)
        editTextDescription.setText(rocket.description)
        checkBoxActive.isChecked = rocket.active
        editTextStages.setText(rocket.stages.toString())
        editTextBoosters.setText(rocket.boosters.toString())
        editTextCost.setText(rocket.costPerLaunch.toString())

        // Configurar el botón de guardar
        view.findViewById<Button>(R.id.buttonSaveRocket).setOnClickListener {
            saveRocket(
                editTextName.text.toString(),
                editTextType.text.toString(),
                editTextDescription.text.toString(),
                checkBoxActive.isChecked,
                editTextStages.text.toString().toIntOrNull(),
                editTextBoosters.text.toString().toIntOrNull(),
                editTextCost.text.toString().toLongOrNull() // Convertir a Long
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
        costPerLaunch: Long? // Cambiado a Long
    ) {
        // Validar que todos los campos sean correctos
        if (name.isBlank() || type.isBlank() || description.isBlank() || stages == null || boosters == null || costPerLaunch == null) {
            Toast.makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        // Actualizar los datos del cohete
        rocket = rocket.copy(
            name = name,
            type = type,
            description = description,
            active = active,
            stages = stages,
            boosters = boosters,
            costPerLaunch = costPerLaunch
        )

        // Guardar los cambios en la base de datos
        lifecycleScope.launch {
            rocketRepository.insertRocket(rocket.toEntity())
            Toast.makeText(requireContext(), "Cohete actualizado con éxito", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp() // Volver al fragmento anterior
        }
    }
}
