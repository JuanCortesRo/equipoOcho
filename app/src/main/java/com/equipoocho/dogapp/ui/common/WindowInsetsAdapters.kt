package com.equipoocho.dogapp.ui.common

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnAttach
import androidx.core.view.updatePadding
import androidx.databinding.BindingAdapter

/**
 * Agrega el alto de la barra de estado como padding superior de la vista.
 *
 * Con targetSdk 35+ la app se dibuja detrás de la barra de estado (edge-to-edge obligatorio). Con este
 * adapter, el fondo de la vista se extiende bajo la barra de estado y su contenido queda por debajo.
 * A diferencia de android:fitsSystemWindows, solo aplica el inset superior: no suma el de la barra de
 * navegación inferior.
 *
 * Uso en XML: app:statusBarPadding="@{true}"
 */
@BindingAdapter("statusBarPadding")
fun View.bindStatusBarPadding(enabled: Boolean) {
    if (!enabled) return
    val initialTop = paddingTop
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val statusBarTop = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
        view.updatePadding(top = initialTop + statusBarTop)
        insets
    }
    // Una vista que se agrega después del primer layout (p. ej., la de un Fragment) no siempre recibe
    // los insets sola, así que se piden de nuevo al adjuntarla a la ventana.
    doOnAttach { it.requestApplyInsets() }
}
