package com.equipoocho.dogapp

import android.app.Application
import com.equipoocho.dogapp.data.local.AppDatabase
import com.equipoocho.dogapp.data.remote.RetrofitClient
import com.equipoocho.dogapp.data.repository.AppointmentRepository
import com.equipoocho.dogapp.data.repository.BreedRepository

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

    val appointmentRepository: AppointmentRepository by lazy {
        AppointmentRepository(database.appointmentDao())
    }

    val breedRepository: BreedRepository by lazy {
        BreedRepository(RetrofitClient.dogApiService)
    }
}
