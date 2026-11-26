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
 * ViewModel de Nueva Cita.
 *
 * Los campos usan two-way binding (@={}); [isFormValid] habilita el botón cuando los cuatro campos de
 * texto tienen contenido. Al guardar, pide a dog.ceo una imagen de la raza (puede ser null si la raza
 * no existe o no hay red; la cita se guarda igual) y navega al Home cuando Room terminó de guardar.
 */
class NewAppointmentViewModel(
    private val appointmentRepository: AppointmentRepository,
    private val breedRepository: BreedRepository
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
            val resolvedImageUrl = breedRepository.getRandomImageUrl(cleanBreed)

            val entity = AppointmentEntity(
                petName = cleanPetName,
                breed = cleanBreed,
                ownerName = cleanOwnerName,
                phone = cleanPhone,
                symptom = cleanSymptom,
                imageUrl = resolvedImageUrl
            )

            appointmentRepository.saveAppointment(entity)

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
            _breeds.value = breedRepository.getBreeds()
        }
    }
}
