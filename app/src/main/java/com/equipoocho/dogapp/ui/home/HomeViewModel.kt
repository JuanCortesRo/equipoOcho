package com.equipoocho.dogapp.ui.home

import androidx.lifecycle.ViewModel

/**
 * HU 2.0 – ViewModel del Home.
 * TODO [DE8 HU2.0] C6: recibir AppointmentRepository y exponer
 *  `appointments: LiveData<List<AppointmentEntity>>` desde Room (repository.getAppointments().asLiveData()).
 */
class HomeViewModel : ViewModel()
