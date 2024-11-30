package com.icb0007_uf1_pr01_robertoanton

class RocketRepository(private val rocketDao: RocketDao) {

    // Obtener todos los cohetes de Room
    suspend fun getAllRockets(): List<RocketEntity> {
        return rocketDao.getAllRockets()
    }

    // Insertar una lista de cohetes en Room
    suspend fun insertAllRockets(rockets: List<RocketEntity>) {
        rocketDao.insertAll(rockets)
    }
}
