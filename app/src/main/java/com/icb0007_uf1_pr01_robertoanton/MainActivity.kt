package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

// Clase principal de la aplicación para gestionar la navegación entre los fragmentos
class MainActivity : AppCompatActivity() {

    // Declaración del NavController para controlar la navegación
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el diseño de la actividad como el contenido de esta actividad
        setContentView(R.layout.activity_main)

        // Configurar la Toolbar personalizada
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // Establece la Toolbar como la ActionBar de esta actividad

        // Configuración del NavHostFragment
        // Este fragmento actúa como un contenedor para manejar la navegación entre otros fragmentos
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController // Obtiene el controlador de navegación

        // Configura la barra de acciones (ActionBar) para trabajar con el NavController
        setupActionBarWithNavController(navController)
    }

    // Método para manejar el botón de retroceso en la barra de acciones
    // Esto asegura que el usuario puede navegar hacia atrás correctamente
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
