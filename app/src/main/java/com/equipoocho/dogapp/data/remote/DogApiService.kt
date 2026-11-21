package com.equipoocho.dogapp.data.remote

import com.equipoocho.dogapp.data.remote.dto.BreedImageResponse
import com.equipoocho.dogapp.data.remote.dto.BreedListResponse
import retrofit2.http.GET
import retrofit2.http.Path

/** CONTRATO COMPARTIDO (HT-03): endpoints de https://dog.ceo/api/ consumidos con Retrofit. */
interface DogApiService {

    /** Lista de razas para el AutoCompleteTextView (HU 3.0 C3 y HU 5.0 C3). */
    @GET("breeds/list/all")
    suspend fun getAllBreeds(): BreedListResponse

    /**
     * Imagen aleatoria de una raza concreta ("Importante" de HU 3.0 y HU 5.0).
     * Se usa la variante por raza de breeds/image/random para que la foto coincida con la raza elegida.
     */
    @GET("breed/{breed}/images/random")
    suspend fun getRandomImageByBreed(@Path("breed") breed: String): BreedImageResponse
}
