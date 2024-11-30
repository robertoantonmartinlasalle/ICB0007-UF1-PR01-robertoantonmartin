package com.icb0007_uf1_pr01_robertoanton

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Anotación @Database define las entidades y la versión de la base de datos.
// Aquí RocketEntity es la única entidad, y la versión es ahora 2.
@Database(entities = [RocketEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    // Método abstracto para acceder al DAO (Data Access Object).
    abstract fun rocketDao(): RocketDao

    companion object {

        // Variable para almacenar la instancia de la base de datos.
        // Volatile garantiza la visibilidad de los cambios entre hilos.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Método para obtener la instancia de la base de datos.
        // Garantiza que solo exista una única instancia en toda la app (Singleton).
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Si no hay instancia, se crea una nueva utilizando Room.databaseBuilder.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // Nombre del archivo de la base de datos.
                )
                    // Opción para recrear la base de datos si hay cambios en su estructura.
                    .fallbackToDestructiveMigration()
                    // Construye la instancia.
                    .build()
                // Asigna la instancia creada a INSTANCE.
                INSTANCE = instance
                instance
            }
        }
    }
}
