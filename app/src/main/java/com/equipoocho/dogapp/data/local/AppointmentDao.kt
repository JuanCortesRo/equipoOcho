package com.equipoocho.dogapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/** CONTRATO COMPARTIDO (HT-02): acceso a la tabla de citas. */
@Dao
interface AppointmentDao {

    @Query("SELECT * FROM appointments ORDER BY id ASC")
    fun getAll(): Flow<List<AppointmentEntity>>

    @Query("SELECT * FROM appointments WHERE id = :id")
    suspend fun getById(id: Int): AppointmentEntity?

    @Insert
    suspend fun insert(appointment: AppointmentEntity): Long

    @Update
    suspend fun update(appointment: AppointmentEntity)

    @Delete
    suspend fun delete(appointment: AppointmentEntity)
}
