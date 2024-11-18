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
import androidx.lifecycle.ViewModelProvider

class LoginFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginViewModel: LoginViewModel

    // Definimos constantes para las credenciales usando companion object
    // El companion object nos permite definir propiedades y funciones que pertenecen a la clase, no a una instancia específica de ella.
    // Al usar `const val`, indicamos que estos valores son constantes y están disponibles en tiempo de compilación, lo cual mejora el rendimiento.
    companion object {
        private const val DEFAULT_USERNAME = "admin"  // Nombre de usuario predeterminado
        private const val DEFAULT_PASSWORD = "1234"   // Contraseña predeterminada
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento a partir del archivo XML
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Inicializar el ViewModel compartido con la actividad
        // Usamos ViewModelProvider para asegurarnos de que los datos persistan incluso si se rota la pantalla
        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        // Referencias a los elementos del diseño
        usernameEditText = view.findViewById(R.id.etUsername)
        passwordEditText = view.findViewById(R.id.etPassword)
        val loginButton = view.findViewById<Button>(R.id.btnLogin)

        // Restaurar datos del ViewModel
        usernameEditText.setText(loginViewModel.username)
        passwordEditText.setText(loginViewModel.password)

        // Lógica del botón de iniciar sesión
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Verificar si los campos no están vacíos
            if (areFieldsNotEmpty(username, password)) {
                // Verificar si las credenciales son correctas
                if (areCredentialsValid(username, password)) {
                    // Mostrar un mensaje de inicio de sesión exitoso
                    Toast.makeText(requireContext(), getString(R.string.login_successful), Toast.LENGTH_SHORT).show()
                    // Redirigir a MainActivity
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    // Mostrar un mensaje de credenciales incorrectas
                    Toast.makeText(requireContext(), getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()
                }
            } else {
                // Mostrar un mensaje si los campos están vacíos
                Toast.makeText(requireContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show()
            }
        }

        // Escuchar cambios en los campos de texto y actualizar el ViewModel cuando los campos pierden el foco
        usernameEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                loginViewModel.username = usernameEditText.text.toString()
            }
        }

        passwordEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                loginViewModel.password = passwordEditText.text.toString()
            }
        }

        return view
    }

    // Método para verificar si los campos no están vacíos
    private fun areFieldsNotEmpty(username: String, password: String): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }

    // Método para validar las credenciales ingresadas
    private fun areCredentialsValid(username: String, password: String): Boolean {
        return username == DEFAULT_USERNAME && password == DEFAULT_PASSWORD
    }
}
