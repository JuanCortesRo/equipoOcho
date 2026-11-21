package com.equipoocho.dogapp.ui.newappointment

import androidx.lifecycle.ViewModel

/**
 * HU 3.0 – ViewModel de Nueva Cita (recibe AppointmentRepository y BreedRepository).
 * TODO [DE8 HU3.0] C2-C5: MutableLiveData<String> petName, breed, ownerName, phone para two-way binding (@={}).
 * TODO [DE8 HU3.0] C3: `breeds: LiveData<List<String>>` cargada desde BreedRepository.getBreeds().
 * TODO [DE8 HU3.0] C6: `symptom` seleccionado; "Síntomas" (posición 0) cuenta como no seleccionado.
 * TODO [DE8 HU3.0] C7/C9: `isFormValid: LiveData<Boolean>` (los 4 campos con texto) que controla el
 *  android:enabled del botón.
 * TODO [DE8 HU3.0] C8: evento "Selecciona un síntoma" si no hay síntoma al guardar.
 * TODO [DE8 HU3.0] C10 + Importante: obtener imageUrl con BreedRepository.getRandomImageUrl(breed),
 *  guardar con Room y emitir el evento de navegar al Home.
 */
class NewAppointmentViewModel : ViewModel()
