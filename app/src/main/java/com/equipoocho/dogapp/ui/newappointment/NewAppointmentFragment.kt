package com.equipoocho.dogapp.ui.newappointment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.equipoocho.dogapp.databinding.FragmentNewAppointmentBinding

/**
 * HU 3.0 – Ventana Nueva Cita.
 * TODO [KAN HU3.0] C1: toolbar curva (layout_curved_toolbar) con el título "Nueva Cita" y flecha rosada
 *  que vuelve al Home.
 * TODO [KAN HU3.0] C2-C5: TextInputLayout outlined con hint gris que sube con el foco y borde morado;
 *  límites: mascota 15, propietario 30, teléfono 10 (solo números). Raza = AutoCompleteTextView (threshold 2).
 * TODO [KAN HU3.0] C6: Spinner con @array/symptoms.
 * TODO [KAN HU3.0] C7-C10: botón "Guardar Cita" rosado con ícono; el texto pasa a blanco bold cuando está habilitado.
 */
class NewAppointmentFragment : Fragment() {

    private var _binding: FragmentNewAppointmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewAppointmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
