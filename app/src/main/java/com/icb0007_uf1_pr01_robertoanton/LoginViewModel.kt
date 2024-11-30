package com.icb0007_uf1_pr01_robertoanton

import androidx.lifecycle.ViewModel

// Clase ViewModel para gestionar los datos relacionados con el inicio de sesión.
class LoginViewModel : ViewModel() {

    // Variable para almacenar el nombre de usuario ingresado.
    var username: String = ""

    // Variable para almacenar la contraseña ingresada.
    var password: String = ""
}
