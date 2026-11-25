package com.equipoocho.dogapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.equipoocho.dogapp.databinding.FragmentHomeBinding

/**
 * Home: lista de citas.
 *
 * Botón atrás: este Fragment no lo intercepta a propósito. La acción action_login_to_home saca el Login
 * del historial (popUpTo inclusive en nav_graph.xml), así que el Home es la primera pantalla y atrás
 * cierra la app en vez de volver al Login.
 *
 * TODO [KAN HU2.0] C6: observar HomeViewModel.appointments y pasar la lista al adapter (submitList).
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val appointmentAdapter = AppointmentAdapter { appointment ->
        findNavController().navigate(HomeFragmentDirections.actionHomeToDetail(appointment.id))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAppointments.adapter = appointmentAdapter

        binding.fabAddAppointment.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToNewAppointment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Suelta la referencia del RecyclerView al adapter: el adapter vive tanto como el Fragment,
        // pero la vista se destruye antes (p. ej., al ir a otra pantalla), y así no se retiene.
        binding.rvAppointments.adapter = null
        _binding = null
    }
}
