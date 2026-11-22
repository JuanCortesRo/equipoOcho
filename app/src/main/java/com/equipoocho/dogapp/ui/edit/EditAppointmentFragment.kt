package com.equipoocho.dogapp.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.equipoocho.dogapp.databinding.FragmentEditAppointmentBinding

/**
 * HU 5.0 – Ventana Editar Cita. Argumento: appointmentId (EditAppointmentFragmentArgs).
 * TODO [KAN HU5.0] C1: toolbar curva con el título "Editar Cita" y flecha rosada que vuelve al Detalle (navigateUp).
 * TODO [KAN HU5.0] C2-C5: mismos campos y reglas que HU 3.0, precargados con la cita.
 * TODO [KAN HU5.0] C6-C8: botón "Editar Cita" rosado con ícono de lápiz; actualiza y va al Home (action_edit_to_home).
 */
class EditAppointmentFragment : Fragment() {

    private var _binding: FragmentEditAppointmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEditAppointmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
