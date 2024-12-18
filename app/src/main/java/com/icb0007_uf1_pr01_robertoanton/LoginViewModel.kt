package com.icb0007_uf1_pr01_robertoanton

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle

class LoginViewModel(application: Application, private val state: SavedStateHandle) : AndroidViewModel(application) {

    companion object {
        private const val PREFS_NAME = "login_prefs"
        private const val KEY_USERNAME = "key_username"
        private const val KEY_PASSWORD = "key_password"
    }

    private val sharedPreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // Propiedades para almacenar el nombre de usuario y contraseña
    var username: String
        get() = state.get<String>(KEY_USERNAME) ?: sharedPreferences.getString(KEY_USERNAME, "") ?: ""
        set(value) {
            state[KEY_USERNAME] = value
            sharedPreferences.edit().putString(KEY_USERNAME, value).apply()
        }

    var password: String
        get() = state.get<String>(KEY_PASSWORD) ?: sharedPreferences.getString(KEY_PASSWORD, "") ?: ""
        set(value) {
            state[KEY_PASSWORD] = value
            sharedPreferences.edit().putString(KEY_PASSWORD, value).apply()
        }
}
