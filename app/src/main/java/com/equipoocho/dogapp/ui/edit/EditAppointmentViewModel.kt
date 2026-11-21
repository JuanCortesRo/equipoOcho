package com.equipoocho.dogapp.ui.edit

import androidx.lifecycle.ViewModel

/**
 * HU 5.0 – ViewModel de Editar Cita (recibe AppointmentRepository y BreedRepository).
 * TODO [DE8 HU5.0] C2: precargar los campos (two-way) desde la cita con el appointmentId recibido.
 * TODO [DE8 HU5.0] C3: `breeds` para el AutoCompleteTextView.
 * TODO [DE8 HU5.0] C6-C7: `isFormValid` con los 4 campos.
 * TODO [DE8 HU5.0] C8 + Importante: si cambió la raza, pedir una nueva imageUrl; actualizar en Room y
 *  emitir el evento de navegar al Home.
 */
class EditAppointmentViewModel : ViewModel()
