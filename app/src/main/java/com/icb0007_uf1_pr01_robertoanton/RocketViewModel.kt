package com.icb0007_uf1_pr01_robertoanton

import androidx.lifecycle.ViewModel

// ViewModel para manejar la lista de cohetes
class RocketViewModel : ViewModel() {
    // Lista de cohetes combinados (API + creados manualmente)
    var allRockets: List<Rocket> = emptyList()
}
