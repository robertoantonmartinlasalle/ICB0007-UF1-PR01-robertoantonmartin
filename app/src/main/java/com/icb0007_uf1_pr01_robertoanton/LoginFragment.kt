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
import androidx.fragment.app.viewModels

class LoginFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    // Instancia del ViewModel usando SavedStateHandle
    private val loginViewModel: LoginViewModel by viewModels()

    companion object {
        private const val DEFAULT_USERNAME = "admin"
        private const val DEFAULT_PASSWORD = "1234"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar la vista del fragmento
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencias a las vistas
        usernameEditText = view.findViewById(R.id.etUsername)
        passwordEditText = view.findViewById(R.id.etPassword)
        loginButton = view.findViewById(R.id.btnLogin)

        // Restaurar valores del ViewModel
        usernameEditText.setText(loginViewModel.username)
        passwordEditText.setText(loginViewModel.password)

        // TextWatcher para actualizar el ViewModel en tiempo real
        usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Actualizar el ViewModel con el valor actual
                loginViewModel.username = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                // No se necesita implementar
            }
        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Actualizar el ViewModel con el valor actual
                loginViewModel.password = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                // No se necesita implementar
            }
        })

        // Acción del botón de inicio de sesión
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == DEFAULT_USERNAME && password == DEFAULT_PASSWORD) {
                Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
