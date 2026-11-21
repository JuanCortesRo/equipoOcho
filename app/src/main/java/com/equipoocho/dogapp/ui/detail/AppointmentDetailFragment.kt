package com.equipoocho.dogapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.equipoocho.dogapp.databinding.FragmentAppointmentDetailBinding

/**
 * HU 4.0 – Ventana Detalle de la Cita. Argumento: appointmentId (AppointmentDetailFragmentArgs).
 * TODO [DE8 HU4.0] C1: toolbar curva con el nombre de la mascota (blanco bold) y flecha rosada que vuelve al Home.
 * TODO [DE8 HU4.0] C2: CardView con elevación, esquinas redondeadas y la imagen con margen, superpuesta a la tarjeta blanca.
 * TODO [DE8 HU4.0] C3: tarjeta blanca con "#id" arriba a la derecha, raza en negro bold, síntoma, línea rosada,
 *  "Propietario: …" y "Teléfono: …".
 * TODO [DE8 HU4.0] C4: FAB rosado de eliminar abajo a la izquierda: elimina y vuelve al Home (action_detail_to_home).
 * TODO [DE8 HU4.0] C5: FAB azul oscuro de editar abajo a la derecha que va a action_detail_to_edit(appointmentId).
 */
class AppointmentDetailFragment : Fragment() {

    private var _binding: FragmentAppointmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAppointmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
