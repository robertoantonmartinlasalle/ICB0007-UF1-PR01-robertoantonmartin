package com.icb0007_uf1_pr01_robertoanton

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Referencias a los elementos del diseño
        val usernameEditText = view.findViewById<EditText>(R.id.etUsername)
        val passwordEditText = view.findViewById<EditText>(R.id.etPassword)
        val loginButton = view.findViewById<Button>(R.id.btnLogin)

        // Lógica del botón de iniciar sesión
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (username == "admin" && password == "1234") {
                    Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    // Redirigir a MainActivity
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                } else {
                    Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Por favor, completa los campos", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
