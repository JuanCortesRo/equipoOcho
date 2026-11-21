package com.equipoocho.dogapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.equipoocho.dogapp.databinding.FragmentLoginBinding

/**
 * HU 1.0 – Ventana Login.
 * TODO [DE8 HU1.0] C1: fondo gris oscuro, sin toolbar.
 * TODO [DE8 HU1.0] C2: cabeza de perro arriba a la derecha (PNG transparente, responsive).
 * TODO [DE8 HU1.0] C3: título "DogApp" blanco, bold, con fuente externa (res/font), centrado bajo la imagen.
 * TODO [DE8 HU1.0] C4: animación Lottie de huella (res/raw) abajo al centro.
 * TODO [DE8 HU1.0] C5-C6: al tocar la huella, mostrar BiometricPrompt (ver BiometricHelper) y,
 *  si la autenticación es correcta, ir a action_login_to_home.
 * TODO [DE8 HU1.0] C7: ícono de la app "dogApp" (mipmap), distinto al que genera Android Studio por defecto.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
