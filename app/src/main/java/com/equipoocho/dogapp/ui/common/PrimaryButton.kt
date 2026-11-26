package com.equipoocho.dogapp.ui.common

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

/**
 * MaterialButton cuyo texto es bold solo mientras está habilitado.
 *
 * El color del texto ya cambia por estado con un selector (button_primary_content), pero el
 * textStyle no se puede variar por estado desde XML; por eso se ajusta en [setEnabled]. Funciona
 * igual si el botón se habilita con DataBinding (android:enabled="@{...}") o desde código.
 *
 * Se usa con el estilo Widget.DogApp.Button.Primary (ver styles.xml).
 */
class PrimaryButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.materialButtonStyle
) : MaterialButton(context, attrs, defStyleAttr) {

    init {
        applyTextStyle(isEnabled)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        applyTextStyle(enabled)
    }

    private fun applyTextStyle(enabled: Boolean) {
        setTypeface(Typeface.create(typeface, if (enabled) Typeface.BOLD else Typeface.NORMAL))
    }
}
