package com.equipoocho.dogapp.data.remote.dto

/**
 * Respuesta de GET https://dog.ceo/api/breed/{raza}/images/random
 * Ejemplo: { "message": "https://images.dog.ceo/breeds/akita/512.jpg", "status": "success" }
 */
data class BreedImageResponse(
    val message: String,
    val status: String
)
