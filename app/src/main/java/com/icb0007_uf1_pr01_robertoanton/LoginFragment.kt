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

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    companion object {
        private const val DEFAULT_USERNAME = "admin"
        private const val DEFAULT_PASSWORD = "1234"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        usernameEditText = view.findViewById(R.id.etUsername)
        passwordEditText = view.findViewById(R.id.etPassword)
        val loginButton = view.findViewById<Button>(R.id.btnLogin)

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
        return view
    }
}
