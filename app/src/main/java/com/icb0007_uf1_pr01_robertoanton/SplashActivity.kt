package com.icb0007_uf1_pr01_robertoanton

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Navegar a la siguiente pantalla después de 3 segundos
        lifecycleScope.launch {
            delay(3000) // Esperar 3 segundos
            val intent = Intent(this@SplashActivity, CredentialActivity::class.java)
            startActivity(intent)
            finish() // Finalizar esta actividad para que no se pueda volver
        }
    }
}
