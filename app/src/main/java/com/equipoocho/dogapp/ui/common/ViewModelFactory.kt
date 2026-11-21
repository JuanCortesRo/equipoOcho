package com.equipoocho.dogapp.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory genérica para crear ViewModels con dependencias, sin Hilt.
 * Uso en un Fragment:
 *   private val viewModel: HomeViewModel by viewModels {
 *       ViewModelFactory { HomeViewModel((requireActivity().application as DogApp).appointmentRepository) }
 *   }
 */
class ViewModelFactory<VM : ViewModel>(
    private val creator: () -> VM
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = creator() as T
}
