package com.equipoocho.dogapp

import android.app.Application
import com.equipoocho.dogapp.data.local.AppDatabase
import com.equipoocho.dogapp.data.repository.AppointmentRepository

/**
 * Application: contenedor de dependencias manual (sin Hilt).
 *
 * Las dependencias se crean con `by lazy`: se construyen la primera vez que se piden y después se
 * reutiliza la misma instancia durante toda la vida de la app.
 *
 * Uso desde un Fragment:
 *   val repository = (requireActivity().application as DogApp).appointmentRepository
 */
class DogApp : Application() {

    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }

    val appointment: AppointmentRepository by lazy {
        AppointmentRepository(database.appointmentDao())
    }

    // TODO [KAN HT-03]: exponer `breedRepository` (by lazy) usando RetrofitClient.
}
