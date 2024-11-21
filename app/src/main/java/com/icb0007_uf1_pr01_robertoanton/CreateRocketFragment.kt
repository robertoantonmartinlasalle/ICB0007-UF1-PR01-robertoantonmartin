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

// Fragmento para crear un nuevo cohete
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
        val saveButton = view.findViewById<Button>(R.id.btnSaveRocket)

        // Configurar la acción del botón de guardar
        saveButton.setOnClickListener {
            val rocketName = rocketNameEditText.text.toString()
            val rocketDescription = rocketDescriptionEditText.text.toString()

            // Validar los campos ingresados
            if (rocketName.isNotEmpty() && rocketDescription.isNotEmpty()) {
                // Lógica para guardar el cohete (puedes ajustar esto para usar una base de datos o API)
                saveRocket(rocketName, rocketDescription)

                // Mostrar un mensaje de éxito
                Toast.makeText(requireContext(), "Cohete creado: $rocketName", Toast.LENGTH_SHORT).show()

                // Navegar de regreso a la lista de cohetes
                findNavController().navigateUp()
            } else {
                // Mostrar un mensaje de error si los campos están vacíos
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    // Función para guardar el cohete (puedes expandirla según sea necesario)
    private fun saveRocket(name: String, description: String) {
        // Aquí puedes agregar la lógica para guardar el cohete.
        // Por ahora, simularemos guardándolo en una lista.
        RocketStorage.addRocket(name, description)
    }
}
