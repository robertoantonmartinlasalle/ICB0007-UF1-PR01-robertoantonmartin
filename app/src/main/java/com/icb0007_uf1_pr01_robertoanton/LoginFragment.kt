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

    // Instancia del ViewModel que usa SavedStateHandle para persistir datos entre recreaciones
    private val loginViewModel: LoginViewModel by viewModels()

    companion object {
        private const val DEFAULT_USERNAME = "admin" // Usuario predeterminado
        private const val DEFAULT_PASSWORD = "1234" // Contraseña predeterminada
    }

    // Método que infla la vista del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    // Método que inicializa las vistas y configura la lógica del fragmento
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Enlazar las vistas del diseño con sus respectivas variables
        usernameEditText = view.findViewById(R.id.etUsername)
        passwordEditText = view.findViewById(R.id.etPassword)
        loginButton = view.findViewById(R.id.btnLogin)

        // Condición para limpiar los datos solo al iniciar la app (no durante la rotación)
        if (savedInstanceState == null && loginViewModel.username.isEmpty() && loginViewModel.password.isEmpty()) {
            loginViewModel.clearData() // Limpia los campos si no hay datos previos
        }

        // Restaurar los valores guardados en el ViewModel
        usernameEditText.setText(loginViewModel.username)
        passwordEditText.setText(loginViewModel.password)

        // Listener para actualizar el ViewModel cuando el usuario escribe en el campo de usuario
        usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.username = s.toString() // Actualiza el nombre de usuario en el ViewModel
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Listener para actualizar el ViewModel cuando el usuario escribe en el campo de contraseña
        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.password = s.toString() // Actualiza la contraseña en el ViewModel
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Configuración del botón de inicio de sesión
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Verifica si las credenciales coinciden con las predeterminadas
            if (username == DEFAULT_USERNAME && password == DEFAULT_PASSWORD) {
                // Muestra un mensaje de inicio de sesión exitoso
                Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                // Limpia los datos antes de navegar a la siguiente actividad
                loginViewModel.clearData()

                // Navega a la MainActivity
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                // Muestra un mensaje de error si las credenciales son incorrectas
                Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
