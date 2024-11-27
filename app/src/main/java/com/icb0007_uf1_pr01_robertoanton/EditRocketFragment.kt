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
import androidx.navigation.fragment.navArgs

class EditRocketFragment : Fragment() {

    private val args: EditRocketFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_rocket, container, false)

        val rocket = args.rocket

        // Referencias a los campos de edición
        val nameEditText = view.findViewById<EditText>(R.id.etEditRocketName)
        val descriptionEditText = view.findViewById<EditText>(R.id.etEditRocketDescription)
        val typeEditText = view.findViewById<EditText>(R.id.etEditRocketType)
        val countryEditText = view.findViewById<EditText>(R.id.etEditRocketCountry)
        val companyEditText = view.findViewById<EditText>(R.id.etEditRocketCompany)
        val costEditText = view.findViewById<EditText>(R.id.etEditRocketCost)
        val successRateEditText = view.findViewById<EditText>(R.id.etEditRocketSuccessRate)
        val heightEditText = view.findViewById<EditText>(R.id.etEditRocketHeight)
        val diameterEditText = view.findViewById<EditText>(R.id.etEditRocketDiameter)

        // Cargar los datos actuales del cohete
        nameEditText.setText(rocket.name)
        descriptionEditText.setText(rocket.description)
        typeEditText.setText(rocket.type)
        countryEditText.setText(rocket.country)
        companyEditText.setText(rocket.company)
        costEditText.setText(rocket.cost_per_launch.toString())
        successRateEditText.setText(rocket.success_rate_pct.toString())
        heightEditText.setText(rocket.height.meters.toString())
        diameterEditText.setText(rocket.diameter.meters.toString())

        // Botón para guardar cambios
        val saveButton = view.findViewById<Button>(R.id.btnSaveEditRocket)
        saveButton.setOnClickListener {
            // Lista para registrar los cambios
            val changes = mutableListOf<String>()

            if (nameEditText.text.toString() != rocket.name) changes.add("Nombre")
            if (descriptionEditText.text.toString() != rocket.description) changes.add("Descripción")
            if (typeEditText.text.toString() != rocket.type) changes.add("Tipo")
            if (countryEditText.text.toString() != rocket.country) changes.add("País")
            if (companyEditText.text.toString() != rocket.company) changes.add("Empresa")
            if (costEditText.text.toString().toInt() != rocket.cost_per_launch) changes.add("Costo por lanzamiento")
            if (successRateEditText.text.toString().toInt() != rocket.success_rate_pct) changes.add("Porcentaje de éxito")
            if (heightEditText.text.toString().toDouble() != rocket.height.meters) changes.add("Altura")
            if (diameterEditText.text.toString().toDouble() != rocket.diameter.meters) changes.add("Diámetro")

            // Crear el cohete actualizado
            val updatedRocket = Rocket(
                name = nameEditText.text.toString(),
                description = descriptionEditText.text.toString(),
                type = typeEditText.text.toString(),
                country = countryEditText.text.toString(),
                company = companyEditText.text.toString(),
                cost_per_launch = costEditText.text.toString().toInt(),
                success_rate_pct = successRateEditText.text.toString().toInt(),
                height = Dimensions(
                    meters = heightEditText.text.toString().toDouble(),
                    feet = heightEditText.text.toString().toDouble() * 3.28084
                ),
                diameter = Dimensions(
                    meters = diameterEditText.text.toString().toDouble(),
                    feet = diameterEditText.text.toString().toDouble() * 3.28084
                ),
                active = rocket.active,
                wikipedia = rocket.wikipedia
            )

            // Actualizar los datos en RocketStorage
            RocketStorage.updateRocket(rocket.name, updatedRocket)

            // Mostrar mensaje con los campos modificados
            if (changes.isNotEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Campos modificados: ${changes.joinToString(", ")}",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(requireContext(), "No se realizaron cambios.", Toast.LENGTH_SHORT).show()
            }

            // Navegar de regreso a la lista
            findNavController().navigateUp()
        }

        return view
    }
}
