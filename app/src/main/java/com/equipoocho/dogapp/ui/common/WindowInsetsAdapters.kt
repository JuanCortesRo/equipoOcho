package com.equipoocho.dogapp.ui.common

import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnAttach
import androidx.core.view.updatePadding
import androidx.databinding.BindingAdapter

/**
 * Agrega el alto de la barra de estado como padding superior de la vista, para que su contenido
 * empiece debajo de ella. El fondo de la vista queda visible detrás de la barra de estado.
 *
 * Con targetSdk 35+ la app se dibuja detrás de la barra de estado (edge-to-edge obligatorio) y el
 * color de la barra definido en el tema se ignora; esto permite conservar una franja de color propio.
 * En versiones anteriores el inset que llega es 0, así que no cambia nada.
 *
 * El inset se marca como consumido: las vistas hijas no lo vuelven a sumar.
 * A diferencia de android:fitsSystemWindows, solo aplica el inset superior (no el de la barra de
 * navegación inferior).
 *
 * Uso en Kotlin: view.applyStatusBarPadding()
 * Uso en XML con DataBinding: app:statusBarPadding="@{true}"
 */
@BindingAdapter("statusBarPadding")
fun View.applyStatusBarPadding(enabled: Boolean = true) {
    if (!enabled) return
    val initialTop = paddingTop
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val statusBarTop = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
        view.updatePadding(top = initialTop + statusBarTop)
        WindowInsetsCompat.Builder(insets)
            .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.NONE)
            .build()
    }
    // Una vista que se agrega después del primer layout no siempre recibe los insets sola,
    // así que se piden de nuevo al adjuntarla a la ventana.
    doOnAttach { it.requestApplyInsets() }
}
