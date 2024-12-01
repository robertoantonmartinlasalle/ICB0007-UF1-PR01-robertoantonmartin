package com.icb0007_uf1_pr01_robertoanton

// Repositorio para manejar operaciones relacionadas con los cohetes en la base de datos
class RocketRepository(private val rocketDao: RocketDao) {

    // Obtener todos los cohetes almacenados en Room
    suspend fun getAllRockets(): List<RocketEntity> {
        // Recuperar todos los cohetes desde la base de datos local
        return rocketDao.getAllRockets()
    }

    // Insertar una lista de cohetes en la base de datos Room
    suspend fun insertAllRockets(rockets: List<RocketEntity>) {
        // Insertar o actualizar todos los cohetes proporcionados
        rocketDao.insertAll(rockets)
    }

    // Insertar un único cohete en la base de datos Room
    suspend fun insertRocket(rocket: RocketEntity) {
        // Insertar o actualizar el cohete proporcionado
        rocketDao.insertRocket(rocket)
    }

    suspend fun deleteRocket(rocket: RocketEntity) {
        rocketDao.delete(rocket)
    }

}
