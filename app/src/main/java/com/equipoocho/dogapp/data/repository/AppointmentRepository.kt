package com.equipoocho.dogapp.data.repository

import com.equipoocho.dogapp.data.local.AppointmentDao
import com.equipoocho.dogapp.data.local.AppointmentEntity
import kotlinx.coroutines.flow.Flow

/**
 * CONTRATO COMPARTIDO: única puerta de entrada de los ViewModels a las citas guardadas en Room.
 *
 * Hilos: no hace falta withContext(Dispatchers.IO). Room ejecuta las funciones suspend y los Flow
 * del DAO en sus propios hilos de fondo, así que se pueden llamar desde viewModelScope sin bloquear la UI.
 */
class AppointmentRepository(private val dao: AppointmentDao) {

    /**
     * Todas las citas, ordenadas por turno (id). Es un Flow: vuelve a emitir cada vez que la tabla
     * cambia, así quien lo observa recibe la lista actualizada al crear, editar o eliminar.
     * En el ViewModel: repository.getAppointments().asLiveData()
     */
    fun getAppointments(): Flow<List<AppointmentEntity>> = dao.getAll()

    /** Una cita por su id (turno), o null si no existe. */
    suspend fun getAppointment(id: Int): AppointmentEntity? = dao.getById(id)

    /**
     * Guarda una cita nueva. Deja id = 0 para que Room lo autogenere.
     * @return el id generado, que es el turno de la cita.
     */
    suspend fun saveAppointment(appointment: AppointmentEntity): Long = dao.insert(appointment)

    /** Actualiza una cita existente; debe traer su id original. */
    suspend fun updateAppointment(appointment: AppointmentEntity) = dao.update(appointment)

    /** Elimina una cita; Room la identifica por su id. */
    suspend fun deleteAppointment(appointment: AppointmentEntity) = dao.delete(appointment)
}
