package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddRocketFragment : Fragment() {

    private lateinit var rocketRepository: RocketRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_rocket, container, false)

        val database = AppDatabase.getDatabase(requireContext())
        rocketRepository = RocketRepository(database.rocketDao())

        // Referenciar todos los campos del diseño XML
        val editTextName = view.findViewById<EditText>(R.id.editTextRocketName)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextRocketDescription)
        val editTextType = view.findViewById<EditText>(R.id.editTextRocketType)
        val switchActive = view.findViewById<Switch>(R.id.switchRocketActive)
        val editTextStages = view.findViewById<EditText>(R.id.editTextRocketStages)
        val editTextBoosters = view.findViewById<EditText>(R.id.editTextRocketBoosters)
        val editTextCost = view.findViewById<EditText>(R.id.editTextRocketCost)
        val editTextSuccessRate = view.findViewById<EditText>(R.id.editTextRocketSuccessRate)
        val editTextFirstFlight = view.findViewById<EditText>(R.id.editTextRocketFirstFlight)
        val editTextCountry = view.findViewById<EditText>(R.id.editTextRocketCountry)
        val editTextCompany = view.findViewById<EditText>(R.id.editTextRocketCompany)
        val editTextHeight = view.findViewById<EditText>(R.id.editTextRocketHeight)
        val editTextDiameter = view.findViewById<EditText>(R.id.editTextRocketDiameter)
        val buttonSave = view.findViewById<Button>(R.id.buttonSaveRocket)

        // Configurar el botón para guardar el nuevo cohete
        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val description = editTextDescription.text.toString()
            val type = editTextType.text.toString()
            val active = switchActive.isChecked
            val stages = editTextStages.text.toString().toIntOrNull() ?: 0
            val boosters = editTextBoosters.text.toString().toIntOrNull() ?: 0
            val costPerLaunch = editTextCost.text.toString().toLongOrNull() ?: 0
            val successRate = editTextSuccessRate.text.toString().toIntOrNull() ?: 0
            val firstFlight = editTextFirstFlight.text.toString()
            val country = editTextCountry.text.toString()
            val company = editTextCompany.text.toString()
            val heightMeters = editTextHeight.text.toString().toDoubleOrNull()
            val diameterMeters = editTextDiameter.text.toString().toDoubleOrNull()

            // Validar los datos
            if (name.isBlank() || description.isBlank() || type.isBlank() || country.isBlank() || company.isBlank()) {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos obligatorios.", Toast.LENGTH_SHORT).show()
            } else {
                val newRocket = RocketEntity(
                    id = System.currentTimeMillis().toString(),
                    name = name,
                    description = description,
                    type = type,
                    active = active,
                    stages = stages,
                    boosters = boosters,
                    costPerLaunch = costPerLaunch,
                    successRate = successRate,
                    firstFlight = firstFlight,
                    country = country,
                    company = company,
                    heightMeters = heightMeters,
                    heightFeet = null,
                    diameterMeters = diameterMeters,
                    diameterFeet = null,
                    wikipedia = "https://es.wikipedia.org/"
                )
                saveRocketToDatabase(newRocket)
            }
        }

        return view
    }

    private fun saveRocketToDatabase(rocket: RocketEntity) {
        lifecycleScope.launch {
            try {
                rocketRepository.insertRocket(rocket)
                Toast.makeText(requireContext(), "Cohete añadido correctamente.", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            } catch (e: Exception) {
                Log.e("AddRocketFragment", "Error al guardar el cohete", e)
                Toast.makeText(requireContext(), "Error al guardar el cohete.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
