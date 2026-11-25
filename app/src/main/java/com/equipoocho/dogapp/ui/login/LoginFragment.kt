package com.equipoocho.dogapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.equipoocho.dogapp.databinding.FragmentLoginBinding

/**
 * HU 1.0 – Ventana Login.
 * TODO [KAN HU1.0] C1: fondo gris oscuro, sin toolbar.
 * TODO [KAN HU1.0] C2: cabeza de perro arriba a la derecha (PNG transparente, responsive).
 * TODO [KAN HU1.0] C3: título "DogApp" blanco, bold, con fuente externa (res/font), centrado bajo la imagen.
 * TODO [KAN HU1.0] C4: animación Lottie de huella (res/raw) abajo al centro.
 * TODO [KAN HU1.0] C5-C6: al tocar la huella, mostrar BiometricPrompt (ver BiometricHelper) y,
 *  si la autenticación es correcta, ir a action_login_to_home.
 * TODO [KAN HU1.0] C7: ícono de la app "dogApp" (mipmap), distinto al que genera Android Studio por defecto.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // KAN-27: al autenticar correctamente se navega a Home (la acción ya limpia Login del back stack).
    // Se crea en onViewCreated (no al tocar la huella): BiometricPrompt debe registrarse antes de
    // mostrarse para recibir bien el callback, también tras una rotación de pantalla.
    private lateinit var biometricHelper: BiometricHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        biometricHelper = BiometricHelper(this) {
            findNavController().navigate(LoginFragmentDirections.actionLoginToHome())
        }
        // KAN-25/26: al tocar la huella (Lottie) se muestra el BiometricPrompt.
        binding.lottieFingerprint.setOnClickListener { biometricHelper.showPrompt() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
