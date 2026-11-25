package com.equipoocho.dogapp.ui.newappointment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.equipoocho.dogapp.data.local.AppointmentEntity
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
    val ownerName = MutableLiveData("")
    val phone = MutableLiveData("")
    val symptomPosition = MutableLiveData(0)
    private val _isFormValid = MediatorLiveData<Boolean>().apply {
        value = false
        addSource(petName) { value = checkFormValid() }
        addSource(breed) { value = checkFormValid() }
        addSource(ownerName) { value = checkFormValid() }
        addSource(phone) { value = checkFormValid() }
    }
    val isFormValid: LiveData<Boolean> = _isFormValid
    private val _selectSymptomEvent = MutableLiveData<Boolean>(false)
    val selectSymptomEvent: LiveData<Boolean> = _selectSymptomEvent
    private val _navigateToHomeEvent = MutableLiveData<Boolean>(false)
    val navigateToHomeEvent: LiveData<Boolean> = _navigateToHomeEvent
    private val _breeds = MutableLiveData<List<String>>(emptyList())
    val breeds: LiveData<List<String>> = _breeds

    init {
        loadBreeds()
    }
    private fun checkFormValid(): Boolean {
        return !petName.value.isNullOrBlank() &&
            !breed.value.isNullOrBlank() &&
            !ownerName.value.isNullOrBlank() &&
            !phone.value.isNullOrBlank()
    }
    fun onSaveClicked(selectedSymptom: String) {
        val currentSymptomPos = symptomPosition.value ?: 0
        if (currentSymptomPos == 0) {
            _selectSymptomEvent.value = true
            return
        }

        val cleanPetName = petName.value.orEmpty().trim()
        val cleanBreed = breed.value.orEmpty().trim()
        val cleanOwnerName = ownerName.value.orEmpty().trim()
        val cleanPhone = phone.value.orEmpty().trim()
        val cleanSymptom = selectedSymptom.trim()

        viewModelScope.launch {
            val resolvedImageUrl = try {
                breedRepository?.getRandomImageUrl(cleanBreed)
            } catch (_: Throwable) {
                null
            }

            val entity = AppointmentEntity(
                petName = cleanPetName,
                breed = cleanBreed,
                ownerName = cleanOwnerName,
                phone = cleanPhone,
                symptom = cleanSymptom,
                imageUrl = resolvedImageUrl
            )

            try {
                appointmentRepository?.saveAppointment(entity)
            } catch (_: Throwable) {
            }

            _navigateToHomeEvent.value = true
        }
    }
    fun consumeSelectSymptomEvent() {
        _selectSymptomEvent.value = false
    }
    fun consumeNavigateToHomeEvent() {
        _navigateToHomeEvent.value = false
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
