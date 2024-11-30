package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el diseño de la actividad
        setContentView(R.layout.activity_main)

        // Configuración de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/") // URL base de la API de SpaceX
            .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON a objetos Kotlin
            .build()

        // Mensaje en Logcat para verificar la configuración
        Log.d("RetrofitTest", "Retrofit configurado correctamente: $retrofit")
    }
}
