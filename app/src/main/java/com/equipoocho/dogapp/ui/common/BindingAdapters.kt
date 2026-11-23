package com.equipoocho.dogapp.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.equipoocho.dogapp.R

/**
 * RECURSO COMPARTIDO: carga una imagen desde una URL con Glide.
 *
 * Uso en XML con DataBinding:
 *   app:imageUrl="@{appointment.imageUrl}"
 *   app:imageUrl="@{appointment.imageUrl}" app:circleCrop="@{true}" para recorte circular
 *
 * - Mientras carga, si la URL es null o si la descarga falla, se muestra ic_pet_placeholder.
 * - Sin circleCrop no se aplica ninguna transformacionn,
 */
@BindingAdapter(value = ["imageUrl", "circleCrop"], requireAll = false)
fun ImageView.bindImageUrl(url: String?, circleCrop: Boolean?) {
    val request = Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_pet_placeholder)
        .error(R.drawable.ic_pet_placeholder)
        .fallback(R.drawable.ic_pet_placeholder)
        .transition(DrawableTransitionOptions.withCrossFade())
    (if (circleCrop == true) request.circleCrop() else request).into(this)
}
