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
 * Muestra la imagen del perro, el título y la animación de huella. Al tocar la huella se abre el
 * BiometricPrompt (ver [BiometricHelper]) y, si la autenticación es correcta, navega a Home
 * con action_login_to_home.
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
