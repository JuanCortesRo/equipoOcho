package com.equipoocho.dogapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.equipoocho.dogapp.data.local.AppointmentEntity
import com.equipoocho.dogapp.data.repository.AppointmentRepository
import kotlinx.coroutines.launch

class AppointmentDetailViewModel(
    private val appointmentRepository: AppointmentRepository,
    appointmentId: Int
) : ViewModel() {

    val appointment: LiveData<AppointmentEntity?> = liveData {
        emit(appointmentRepository.getAppointment(appointmentId))
    }

    private val _navigateHomeEvent = MutableLiveData(false)
    val navigateHomeEvent: LiveData<Boolean> = _navigateHomeEvent

    fun deleteAppointment() {
        viewModelScope.launch {
            appointment.value?.let { appointmentRepository.deleteAppointment(it) }
            _navigateHomeEvent.value = true
        }
    }

    fun consumeNavigateHomeEvent() {
        _navigateHomeEvent.value = false
    }
}
