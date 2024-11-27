package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class CreateRocketFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_create_rocket, container, false)

        // Referencias a los elementos del diseño
        val rocketNameEditText = view.findViewById<EditText>(R.id.etRocketName)
        val rocketDescriptionEditText = view.findViewById<EditText>(R.id.etRocketDescription)
        val rocketTypeEditText = view.findViewById<EditText>(R.id.etRocketType)
        val rocketCountryEditText = view.findViewById<EditText>(R.id.etRocketCountry)
        val rocketCompanyEditText = view.findViewById<EditText>(R.id.etRocketCompany)
        val rocketCostEditText = view.findViewById<EditText>(R.id.etRocketCost)
        val rocketSuccessRateEditText = view.findViewById<EditText>(R.id.etRocketSuccessRate)
        val rocketHeightEditText = view.findViewById<EditText>(R.id.etRocketHeight)
        val rocketDiameterEditText = view.findViewById<EditText>(R.id.etRocketDiameter)
        val saveButton = view.findViewById<Button>(R.id.btnSaveRocket)

        // Configurar la acción del botón de guardar
        saveButton.setOnClickListener {
            val rocketName = rocketNameEditText.text.toString().trim()
            val rocketDescription = rocketDescriptionEditText.text.toString().trim()
            val rocketType = rocketTypeEditText.text.toString().trim()
            val rocketCountry = rocketCountryEditText.text.toString().trim()
            val rocketCompany = rocketCompanyEditText.text.toString().trim()
            val rocketCost = rocketCostEditText.text.toString().toIntOrNull() ?: 0
            val rocketSuccessRate = rocketSuccessRateEditText.text.toString().toIntOrNull() ?: 0
            val rocketHeight = rocketHeightEditText.text.toString().toDoubleOrNull() ?: 0.0
            val rocketDiameter = rocketDiameterEditText.text.toString().toDoubleOrNull() ?: 0.0

            // Validar los campos obligatorios
            if (rocketName.isEmpty() || rocketDescription.isEmpty() || rocketType.isEmpty() ||
                rocketCountry.isEmpty() || rocketCompany.isEmpty()
            ) {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear el cohete con los nuevos datos
            RocketStorage.addRocket(
                name = rocketName,
                description = rocketDescription,
                type = rocketType,
                country = rocketCountry,
                company = rocketCompany,
                cost_per_launch = rocketCost,
                success_rate_pct = rocketSuccessRate,
                height = rocketHeight,
                diameter = rocketDiameter
            )

            // Mostrar un mensaje de éxito
            Toast.makeText(requireContext(), "Cohete creado: $rocketName", Toast.LENGTH_SHORT).show()

            // Navegar de regreso a la lista de cohetes
            findNavController().navigateUp()
        }

        return view // Retornar la vista inflada
    }
}
