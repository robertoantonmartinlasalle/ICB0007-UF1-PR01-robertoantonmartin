package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CredentialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credential)

        // Cargar el LoginFragment al iniciar
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment())
            .commit()
    }
}
