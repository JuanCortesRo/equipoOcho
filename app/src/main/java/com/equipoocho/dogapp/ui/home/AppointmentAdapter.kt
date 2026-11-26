package com.equipoocho.dogapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.equipoocho.dogapp.data.local.AppointmentEntity
import com.equipoocho.dogapp.databinding.ItemAppointmentBinding

/**
 * Adapter de la lista de citas: cada ítem se dibuja con item_appointment.xml (DataBinding).
 *
 * Es un ListAdapter: basta con llamar submitList(nuevaLista) y DiffUtil calcula en segundo plano qué
 * ítems se agregaron, cambiaron o eliminaron, animando solo esos en vez de redibujar toda la lista.
 *
 * @param onItemClick se llama con la cita del ítem tocado; quien crea el adapter decide qué hacer
 *  (el adapter no navega ni conoce otras pantallas).
 */
class AppointmentAdapter(
    private val onItemClick: (AppointmentEntity) -> Unit
) : ListAdapter<AppointmentEntity, AppointmentAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(ItemAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        // El listener se asigna una sola vez por ViewHolder (no en cada bind) y busca la cita por la
        // posición actual, así sigue siendo correcto aunque la lista cambie o el ítem se recicle.
        holder.itemView.setOnClickListener {
            val position = holder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) onItemClick(getItem(position))
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(private val binding: ItemAppointmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(appointment: AppointmentEntity) {
            binding.appointment = appointment
            // Aplica el binding de inmediato: sin esto, al hacer scroll rápido un ítem reciclado podría
            // mostrar por un instante los datos del anterior.
            binding.executePendingBindings()
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<AppointmentEntity>() {
        // Mismo ítem: misma cita (el id es único y lo asigna Room).
        override fun areItemsTheSame(oldItem: AppointmentEntity, newItem: AppointmentEntity) =
            oldItem.id == newItem.id

        // Mismo contenido: AppointmentEntity es data class, así que == compara todos los campos.
        override fun areContentsTheSame(oldItem: AppointmentEntity, newItem: AppointmentEntity) =
            oldItem == newItem
    }
}
