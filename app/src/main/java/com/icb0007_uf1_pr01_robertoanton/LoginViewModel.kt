package com.icb0007_uf1_pr01_robertoanton

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

// ViewModel para persistir datos de inicio de sesión
class LoginViewModel(private val state: SavedStateHandle) : ViewModel() {

    companion object {
        private const val KEY_USERNAME = "key_username"
        private const val KEY_PASSWORD = "key_password"
    }

    // Propiedades para almacenar el nombre de usuario y contraseña
    var username: String
        get() = state.get<String>(KEY_USERNAME) ?: ""
        set(value) {
            state[KEY_USERNAME] = value
        }

    var password: String
        get() = state.get<String>(KEY_PASSWORD) ?: ""
        set(value) {
            state[KEY_PASSWORD] = value
        }
}
