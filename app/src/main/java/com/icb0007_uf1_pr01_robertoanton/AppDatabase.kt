package com.icb0007_uf1_pr01_robertoanton

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Declaración de la base de datos de Room, especificando la entidad (RocketEntity) y la versión.
@Database(entities = [RocketEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // Declaración del DAO (Data Access Object) que interactúa con la base de datos.
    abstract fun rocketDao(): RocketDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Obtiene una instancia de la base de datos.
         *
         * - Si ya existe una instancia (singleton), la reutiliza.
         * - Si no existe, crea una nueva instancia usando Room.
         * - `.fallbackToDestructiveMigration()` asegura que la base de datos se elimine y recree
         *   si se detecta un cambio en el esquema (ideal para desarrollo).
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // Nombre del archivo de la base de datos
                )
                    .fallbackToDestructiveMigration() // Eliminar y recrear la base de datos si hay cambios en el esquema
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
