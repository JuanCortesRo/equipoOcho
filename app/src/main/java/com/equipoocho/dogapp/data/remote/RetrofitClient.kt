package com.equipoocho.dogapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Cliente HTTP de la API pública https://dog.ceo/api/.
 *
 * Es un `object` (una sola instancia) y construye Retrofit con `by lazy`: crear Retrofit y su
 * OkHttpClient interno es costoso, así que se hace una vez, la primera vez que se pide [dogApiService].
 * GsonConverterFactory convierte el JSON de las respuestas en los DTO del paquete `dto`.
 */
object RetrofitClient {

    /** Debe terminar en "/": Retrofit resuelve las rutas de [DogApiService] relativas a esta URL. */
    const val BASE_URL = "https://dog.ceo/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val dogApiService: DogApiService by lazy { retrofit.create(DogApiService::class.java) }
}
