package com.equipoocho.dogapp.ui.detail

import androidx.lifecycle.ViewModel

/**
 * HU 4.0 – ViewModel del Detalle (recibe AppointmentRepository).
 * TODO [KAN HU4.0] C1-C3: cargar `appointment: LiveData<AppointmentEntity?>` por appointmentId (Safe Args).
 * TODO [KAN HU4.0] C4: delete() elimina de Room y emite el evento de navegar al Home.
 */
class AppointmentDetailViewModel : ViewModel()
