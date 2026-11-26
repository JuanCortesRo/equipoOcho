package com.equipoocho.dogapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.equipoocho.dogapp.data.local.AppointmentEntity
import com.equipoocho.dogapp.data.repository.AppointmentRepository

/**
 * ViewModel del Home: expone las citas guardadas en Room.
 *
 * [appointments] viene del Flow del repositorio convertido a LiveData: Room vuelve a emitir cada vez
 * que la tabla cambia, así que la lista se actualiza sola al crear, editar o eliminar una cita, sin
 * recargarla a mano. La consulta corre en segundo plano y solo mientras haya un observador activo.
 */
class HomeViewModel(repository: AppointmentRepository) : ViewModel() {

    val appointments: LiveData<List<AppointmentEntity>> = repository.getAppointments().asLiveData()
}
