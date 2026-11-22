package com.equipoocho.dogapp.data.repository

import android.util.Log
import com.equipoocho.dogapp.data.remote.DogApiService
import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.io.IOException

/**
 * CONTRATO COMPARTIDO: acceso a la API dog.ceo (razas e imágenes).
 *
 * Errores: ningún metodo lanza excepciones por fallos de red, HTTP o JSON. Devuelven una lista vacía
 * o null y registran el error en Logcat (tag "BreedRepository"), así quien llama decide qué mostrar.
 *
 * Hilos: las funciones suspend de Retrofit ya se ejecutan fuera del hilo principal, así que se pueden
 * llamar desde viewModelScope sin withContext(Dispatchers.IO).
 */
class BreedRepository(private val api: DogApiService) {

    // La lista de razas no cambia durante la ejecución: se descarga una vez y se reutiliza.
    @Volatile
    private var cachedBreeds: List<String>? = null

    /**
     * Lista plana y ordenada de razas, p. ej. ["pug", "dalmata", ...]
     * @return la lista, o una lista vacía si falla la red. Un fallo no se cachea: el siguiente
     * llamado lo intenta de nuevo
     */
    suspend fun getBreeds(): List<String> {
        cachedBreeds?.let { return it }
        return safeApiCall("getBreeds") {
            api.getAllBreeds().message.keys.sorted()
        }?.also { cachedBreeds = it } ?: emptyList()
    }

    /**
     * URL de una imagen aleatoria de [breed]. La raza se normaliza porque dog.ceo solo reconoce nombres en minúscula
     * @return la URL, o null si la raza está vacía, no existe en dog.ceo (HTTP 404) o falla la red.
     */
    suspend fun getRandomImageUrl(breed: String): String? {
        val normalized = breed.trim().lowercase()
        if (normalized.isEmpty()) return null
        return safeApiCall("getRandomImageUrl($normalized)") {
            api.getRandomImageByBreed(normalized).message
        }
    }

    /**
     * Ejecuta [block] y convierte los errores esperables de red, HTTP o JSON en null.
     */
    private suspend fun <T> safeApiCall(operation: String, block: suspend () -> T): T? =
        try {
            block()
        } catch (e: IOException) {
            Log.w(TAG, "$operation: sin conexión o timeout", e)
            null
        } catch (e: HttpException) {
            Log.w(TAG, "$operation: HTTP ${e.code()}", e)
            null
        } catch (e: JsonParseException) {
            Log.w(TAG, "$operation: respuesta con formato inesperado", e)
            null
        }

    private companion object {
        const val TAG = "BreedRepository"
    }
}
