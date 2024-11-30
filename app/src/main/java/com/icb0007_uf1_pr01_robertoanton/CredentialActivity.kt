package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// Esta actividad es responsable de gestionar las credenciales del usuario y cargar el LoginFragment al inicio.
class CredentialActivity : AppCompatActivity() {

    // Método que se llama cuando la actividad es creada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credential) // Configura el diseño asociado a esta actividad

        // Inicia el LoginFragment dentro del contenedor definido en activity_credential.xml
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment()) // Reemplaza el contenido del contenedor con LoginFragment
            .commit() // Confirma y aplica la transacción
    }

    // Método llamado para guardar el estado de la actividad antes de que se destruya temporalmente
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Por ahora, no guardamos ningún estado específico porque no hay datos dinámicos que recuperar
    }

    // Método llamado para restaurar el estado de la actividad cuando se recrea
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Actualmente, no restauramos ningún estado porque no hay datos dinámicos que recuperar
    }
}
