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
        // Inflar el diseño del fragmento desde el archivo XML correspondiente
        val view = inflater.inflate(R.layout.fragment_create_rocket, container, false)

        // Referencias a los elementos del diseño para capturar los datos del usuario
        val rocketNameEditText = view.findViewById<EditText>(R.id.etRocketName) // Nombre del cohete
        val rocketDescriptionEditText = view.findViewById<EditText>(R.id.etRocketDescription) // Descripción
        val rocketTypeEditText = view.findViewById<EditText>(R.id.etRocketType) // Tipo del cohete
        val rocketCountryEditText = view.findViewById<EditText>(R.id.etRocketCountry) // País
        val rocketCompanyEditText = view.findViewById<EditText>(R.id.etRocketCompany) // Empresa
        val saveButton = view.findViewById<Button>(R.id.btnSaveRocket) // Botón para guardar el cohete

        // Configurar la acción del botón de guardar
        saveButton.setOnClickListener {
            // Obtener los valores ingresados por el usuario
            val rocketName = rocketNameEditText.text.toString().trim() // Eliminar espacios innecesarios
            val rocketDescription = rocketDescriptionEditText.text.toString().trim()
            val rocketType = rocketTypeEditText.text.toString().trim()
            val rocketCountry = rocketCountryEditText.text.toString().trim()
            val rocketCompany = rocketCompanyEditText.text.toString().trim()

            // Validar los campos ingresados
            if (rocketName.isEmpty() || rocketDescription.isEmpty() || rocketType.isEmpty() ||
                rocketCountry.isEmpty() || rocketCompany.isEmpty()
            ) {
                // Mostrar un mensaje si hay campos vacíos
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Detener la ejecución si hay errores
            }

            // Crear el cohete y agregarlo a RocketStorage
            RocketStorage.addRocket(
                name = rocketName,
                description = rocketDescription,
                type = rocketType,
                country = rocketCountry,
                company = rocketCompany
            )

            // Mostrar un mensaje de éxito
            Toast.makeText(requireContext(), "Cohete creado: $rocketName", Toast.LENGTH_SHORT).show()

            // Navegar de regreso a la lista de cohetes
            findNavController().navigateUp()
        }

        return view // Retornar la vista inflada para el fragmento
    }
}
