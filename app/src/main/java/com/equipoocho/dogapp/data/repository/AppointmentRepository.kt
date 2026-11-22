package com.equipoocho.dogapp.data.repository

import com.equipoocho.dogapp.data.local.AppointmentDao
import com.equipoocho.dogapp.data.local.AppointmentEntity
import kotlinx.coroutines.flow.Flow

/**
 * CONTRATO COMPARTIDO (HT-02): única puerta de entrada de los ViewModels a las citas.
 * Lo usan HU 2.0 (listar), HU 3.0 (crear), HU 4.0 (detalle/eliminar) y HU 5.0 (editar).
 */
class AppointmentRepository(private val dao: AppointmentDao) {

    fun getAppointments(): Flow<List<AppointmentEntity>> = TODO("KAN HT-02")

    suspend fun getAppointment(id: Int): AppointmentEntity? = TODO("KAN HT-02")

    suspend fun saveAppointment(appointment: AppointmentEntity): Long = TODO("KAN HT-02")

    suspend fun updateAppointment(appointment: AppointmentEntity): Unit = TODO("KAN HT-02")

    suspend fun deleteAppointment(appointment: AppointmentEntity): Unit = TODO("KAN HT-02")
}
