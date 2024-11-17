package com.icb0007_uf1_pr01_robertoanton

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Añadir texto dinámicamente debajo del logo
        val splashLayout = findViewById<RelativeLayout>(R.id.splashLayout)
        val logoImage = findViewById<ImageView>(R.id.logoImage) // Cambiado a ImageView

        // Crear un TextView programáticamente
        val appNameTextView = TextView(this).apply {
            text = "by Roberto Anton" // Texto de la aplicación
            setTextColor(Color.WHITE) // Color del texto
            textSize = 24f // Tamaño de la fuente
            gravity = Gravity.CENTER // Centrar el texto
        }

        // Definir las reglas de posición para el TextView
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            addRule(RelativeLayout.BELOW, R.id.logoImage) // Colocarlo debajo del logo
            addRule(RelativeLayout.CENTER_HORIZONTAL) // Centrarlo horizontalmente
            setMargins(0, 16, 0, 0) // Añadir margen superior de 16dp
        }

        // Añadir el TextView al diseño
        splashLayout.addView(appNameTextView, params)

        // Navegar a la siguiente pantalla después de 3 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, CredentialActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3 segundos

    }
}
