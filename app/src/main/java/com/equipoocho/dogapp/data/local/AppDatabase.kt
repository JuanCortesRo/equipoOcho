package com.equipoocho.dogapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Base de datos local SQLite con Room (HU 2.0 C6).
 * TODO [DE8 HT-02]: construir la instancia única (Room.databaseBuilder) y exponerla desde DogApp.
 */
@Database(entities = [AppointmentEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appointmentDao(): AppointmentDao

    companion object {
        const val DATABASE_NAME = "dogapp.db"
    }
}
