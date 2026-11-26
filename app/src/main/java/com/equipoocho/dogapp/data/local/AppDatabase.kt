package com.equipoocho.dogapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Base de datos local SQLite con Room.
 *
 * Singleton: abrir una base de datos Room es costoso, así que toda la app comparte una sola instancia.
 * Se obtiene con [getInstance].
 */
@Database(entities = [AppointmentEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appointmentDao(): AppointmentDao

    companion object {
        const val DATABASE_NAME = "dogapp.db"

        // @Volatile: cualquier hilo ve de inmediato la instancia que otro hilo acaba de crear.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Devuelve la instancia única de la base de datos y la crea si todavía no existe.
         * Usa doble verificación con synchronized para que dos hilos no creen dos instancias.
         */
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                // applicationContext: si se pasara una Activity o un Fragment, el singleton los
                // retendría y se filtraría memoria.
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                // No hay migraciones definidas. Si alguien cambia AppointmentEntity, se sube la
                // versión y Room recrea la BD (se pierden los datos de prueba) en vez de cerrar la app.
                .fallbackToDestructiveMigration(dropAllTables = true)
                .build()
    }
}
