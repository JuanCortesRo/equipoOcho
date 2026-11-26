package com.equipoocho.dogapp.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.equipoocho.dogapp.data.local.AppointmentEntity
import com.equipoocho.dogapp.data.repository.AppointmentRepository
import com.equipoocho.dogapp.data.repository.BreedRepository
import kotlinx.coroutines.launch

class EditAppointmentViewModel(
    private val appointmentRepository: AppointmentRepository,
    private val breedRepository: BreedRepository,
    appointmentId: Int
) : ViewModel() {

    val petName = MutableLiveData("")
    val breed = MutableLiveData("")
    val ownerName = MutableLiveData("")
    val phone = MutableLiveData("")

    private val _isFormValid = MediatorLiveData<Boolean>().apply {
        value = false
        addSource(petName) { value = checkFormValid() }
        addSource(breed) { value = checkFormValid() }
        addSource(ownerName) { value = checkFormValid() }
        addSource(phone) { value = checkFormValid() }
    }
    val isFormValid: LiveData<Boolean> = _isFormValid

    private val _breeds = MutableLiveData<List<String>>(emptyList())
    val breeds: LiveData<List<String>> = _breeds

    private val _navigateToHomeEvent = MutableLiveData(false)
    val navigateToHomeEvent: LiveData<Boolean> = _navigateToHomeEvent

    private var originalAppointment: AppointmentEntity? = null

    init {
        viewModelScope.launch {
            originalAppointment = appointmentRepository.getAppointment(appointmentId)
            originalAppointment?.let { appointment ->
                petName.value = appointment.petName
                breed.value = appointment.breed
                ownerName.value = appointment.ownerName
                phone.value = appointment.phone
            }
        }
        viewModelScope.launch {
            _breeds.value = breedRepository.getBreeds()
        }
    }

    private fun checkFormValid(): Boolean =
        !petName.value.isNullOrBlank() &&
            !breed.value.isNullOrBlank() &&
            !ownerName.value.isNullOrBlank() &&
            !phone.value.isNullOrBlank()

    fun onEditClicked() {
        if (isFormValid.value != true) return
        val original = originalAppointment ?: return

        val cleanPetName = petName.value.orEmpty().trim()
        val cleanBreed = breed.value.orEmpty().trim()
        val cleanOwnerName = ownerName.value.orEmpty().trim()
        val cleanPhone = phone.value.orEmpty().trim()

        viewModelScope.launch {
            val imageUrl = if (cleanBreed.equals(original.breed.trim(), ignoreCase = true)) {
                original.imageUrl
            } else {
                breedRepository.getRandomImageUrl(cleanBreed)
            }

            appointmentRepository.updateAppointment(
                original.copy(
                    petName = cleanPetName,
                    breed = cleanBreed,
                    ownerName = cleanOwnerName,
                    phone = cleanPhone,
                    imageUrl = imageUrl
                )
            )
            _navigateToHomeEvent.value = true
        }
    }

    fun consumeNavigateToHomeEvent() {
        _navigateToHomeEvent.value = false
    }
}
