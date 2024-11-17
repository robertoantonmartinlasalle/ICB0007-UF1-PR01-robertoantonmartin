package com.icb0007_uf1_pr01_robertoanton

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class LoginFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Inicializar el ViewModel compartido con la actividad
        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        // Referencias a los elementos del diseño
        usernameEditText = view.findViewById(R.id.etUsername)
        passwordEditText = view.findViewById(R.id.etPassword)
        val loginButton = view.findViewById<Button>(R.id.btnLogin)

        // Restaurar datos del ViewModel
        usernameEditText.setText(loginViewModel.username)
        passwordEditText.setText(loginViewModel.password)

        // Escuchar cambios en los campos de texto y actualizar el ViewModel
        usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.username = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.password = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Lógica del botón de iniciar sesión
        loginButton.setOnClickListener {
            val username = loginViewModel.username
            val password = loginViewModel.password

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
