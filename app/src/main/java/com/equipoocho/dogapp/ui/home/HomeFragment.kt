package com.equipoocho.dogapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.equipoocho.dogapp.databinding.FragmentHomeBinding

/**
 * HU 2.0 – Home Administrador de Citas.
 * TODO [KAN HU2.0] C1: el botón atrás debe salir al escritorio. Se resuelve con popUpTo inclusive
 *  en action_login_to_home (nav_graph); verificarlo en el dispositivo.
 * TODO [KAN HU2.0] C2: fondo blanco; header gris oscuro con la esquina inferior izquierda curva, título
 *  "Administrador de Citas" blanco bold a la izquierda y centrado verticalmente; imagen del perro a la
 *  derecha con "DogApp" debajo.
 * TODO [KAN HU2.0] C3/C5/C6: RecyclerView con AppointmentAdapter que observa HomeViewModel.appointments.
 * TODO [KAN HU2.0] C4: FAB rosado #F05861 abajo a la derecha que lleva a action_home_to_newAppointment.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
