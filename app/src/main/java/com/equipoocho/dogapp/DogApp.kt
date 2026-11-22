package com.equipoocho.dogapp

import android.app.Application

/**
 * Application: contenedor de dependencias manual (sin Hilt).
 * TODO [KAN HT-02]: exponer `database` y `appointmentRepository` (by lazy).
 * TODO [KAN HT-03]: exponer `breedRepository` (by lazy) usando RetrofitClient.
 */
class DogApp : Application()
