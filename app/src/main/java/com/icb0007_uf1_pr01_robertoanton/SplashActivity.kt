package com.icb0007_uf1_pr01_robertoanton

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Clase que representa la pantalla de inicio o Splash Screen
class SplashActivity : AppCompatActivity() {

    // Método onCreate: se llama cuando la actividad se crea por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Establecer el diseño de la actividad

        // Usamos lifecycleScope para ejecutar una tarea en segundo plano
        lifecycleScope.launch {
            delay(3000) // Esperar 3 segundos antes de navegar a la siguiente pantalla
            val intent = Intent(this@SplashActivity, CredentialActivity::class.java)
            startActivity(intent) // Iniciar la actividad de credenciales
            finish() // Finalizar esta actividad para que no se pueda volver
        }
    }
}
