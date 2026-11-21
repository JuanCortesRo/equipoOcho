package com.equipoocho.dogapp.data.repository

import com.equipoocho.dogapp.data.remote.DogApiService

/**
 * CONTRATO COMPARTIDO (HT-03): acceso a la API dog.ceo.
 * Lo usan HU 3.0 y HU 5.0 (autocompletar raza e imagen de la mascota).
 */
class BreedRepository(private val api: DogApiService) {

    /** Lista plana de razas, p. ej. ["akita", "bulldog", "pug", ...]. */
    suspend fun getBreeds(): List<String> = TODO("DE8 HT-03")

    /** URL de una imagen aleatoria de [breed], o null si la raza no existe o falla la red. */
    suspend fun getRandomImageUrl(breed: String): String? = TODO("DE8 HT-03")
}
