package com.equipoocho.dogapp.ui.newappointment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.equipoocho.dogapp.data.repository.AppointmentRepository
import com.equipoocho.dogapp.data.repository.BreedRepository
import kotlinx.coroutines.launch

/**
 * HU 3.0 – ViewModel de Nueva Cita (recibe AppointmentRepository y BreedRepository).
 * TODO [KAN HU3.0] C2-C5: MutableLiveData<String> ownerName, phone para two-way binding (@={}).
 * TODO [KAN HU3.0] C6: `symptom` seleccionado; "Síntomas" (posición 0) cuenta como no seleccionado.
 * TODO [KAN HU3.0] C7/C9: `isFormValid: LiveData<Boolean>` (los 4 campos con texto) que controla el
 *  android:enabled del botón.
 * TODO [KAN HU3.0] C8: evento "Selecciona un síntoma" si no hay síntoma al guardar.
 * TODO [KAN HU3.0] C10 + Importante: obtener imageUrl con BreedRepository.getRandomImageUrl(breed),
 *  guardar con Room y emitir el evento de navegar al Home.
 */
class NewAppointmentViewModel(
    private val appointmentRepository: AppointmentRepository? = null,
    private val breedRepository: BreedRepository? = null
) : ViewModel() {

    val petName = MutableLiveData("")
    val breed = MutableLiveData("")

    private val _breeds = MutableLiveData<List<String>>(emptyList())
    val breeds: LiveData<List<String>> = _breeds

    init {
        loadBreeds()
    }

    private fun loadBreeds() {
        viewModelScope.launch {
            _breeds.value = try {
                breedRepository?.getBreeds() ?: emptyList()
            } catch (_: Throwable) {
                emptyList()
            }
        }
    }
}
