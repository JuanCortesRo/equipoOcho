package com.equipoocho.dogapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * CONTRATO COMPARTIDO: entidad de una cita en Room. Cambiarla afecta a todo el equipo;
 * avisa antes de modificarla.
 *
 * [id] es el "turno" que se muestra como "#n" en Home (HU 2.0 C3) y en Detalle (HU 4.0 C3).
 * [imageUrl] es la URL que se obtiene de dog.ceo para la raza al crear o editar la cita.
 */
@Entity(tableName = "appointments")
data class AppointmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val petName: String,   // máximo 15 caracteres
    val breed: String,
    val ownerName: String, // máximo 30 caracteres
    val phone: String,     // solo números, máximo 10
    val symptom: String,
    val imageUrl: String? = null
)
