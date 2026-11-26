package com.equipoocho.dogapp.ui.login

import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.equipoocho.dogapp.R

/**
 * Encapsula BiometricPrompt (androidx.biometric).
 *
 * KAN-26 (HU 1.0 C5): PromptInfo con título, subtítulo y botón negativo "Cancelar".
 * KAN-27 (HU 1.0 C6): si la huella es válida se ejecuta [onSuccess]; el Fragment decide a dónde ir.
 *
 * Por qué recibe un Fragment y no un Context:
 *  - BiometricPrompt(fragment, ...) engancha su ciclo de vida al del Fragment, así que el diálogo
 *    sobrevive a rotaciones y se limpia solo cuando el Fragment se destruye.
 */
class BiometricHelper(
    private val fragment: Fragment,
    private val onSuccess: () -> Unit
) {

    // El resultado del prompt debe entregarse en el hilo principal (UI).
    private val executor = ContextCompat.getMainExecutor(fragment.requireContext())

    private val callback = object : BiometricPrompt.AuthenticationCallback() {

        // Huella reconocida -> aquí (y solo aquí) se permite avanzar.
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            onSuccess()
        }

        // Huella no válida / cancelar / error: el propio sistema muestra el mensaje o cierra el
        // diálogo (requisito de la HU). No hay que hacer nada extra.
    }

    private val prompt = BiometricPrompt(fragment, executor, callback)

    private val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle(fragment.getString(R.string.biometric_title))
        .setSubtitle(fragment.getString(R.string.biometric_subtitle))
        // El botón negativo es obligatorio cuando NO se permite credencial de dispositivo (PIN).
        .setNegativeButtonText(fragment.getString(R.string.biometric_cancel))
        // BIOMETRIC_STRONG = huella o rostro de clase 3; es lo que pide la HU (huella digital).
        .setAllowedAuthenticators(BIOMETRIC_STRONG)
        .build()

    /** Muestra el diálogo del sistema. Llamar cuando el usuario toque la huella. */
    fun showPrompt() {
        prompt.authenticate(promptInfo)
    }
}
