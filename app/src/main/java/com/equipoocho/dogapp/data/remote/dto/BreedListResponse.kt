package com.equipoocho.dogapp.data.remote.dto

/**
 * Respuesta de GET https://dog.ceo/api/breeds/list/all
 * Ejemplo: { "message": { "bulldog": ["boston", "french"], "pug": [] }, "status": "success" }
 */
data class BreedListResponse(
    val message: Map<String, List<String>>,
    val status: String
)
