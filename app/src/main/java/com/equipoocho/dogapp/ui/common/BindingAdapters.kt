package com.equipoocho.dogapp.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * CONTRATO COMPARTIDO (HT-01): binding adapters que usan los layouts.
 * Uso en XML: app:imageUrl="@{appointment.imageUrl}" app:circleCrop="@{true}"
 */
@BindingAdapter(value = ["imageUrl", "circleCrop"], requireAll = false)
fun ImageView.bindImageUrl(url: String?, circleCrop: Boolean?) {
    // TODO [DE8 HT-01]: cargar con Glide (placeholder/error) y aplicar circleCrop() cuando circleCrop == true
    //  (círculo en el ítem de Home, HU 2.0 C3; imagen rectangular en el Detalle, HU 4.0 C2).
}
