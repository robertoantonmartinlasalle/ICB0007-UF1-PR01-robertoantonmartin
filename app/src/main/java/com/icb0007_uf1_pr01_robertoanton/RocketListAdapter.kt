package com.icb0007_uf1_pr01_robertoanton

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador para la lista de cohetes
class RocketListAdapter(
    private val rockets: List<Rocket>,
    private val onRocketClicked: (Rocket) -> Unit // Callback para manejar clics en los ítems
) : RecyclerView.Adapter<RocketListAdapter.RocketViewHolder>() {

    // ViewHolder que maneja las vistas de cada ítem
    class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewRocketName)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewRocketDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rocket, parent, false) // Inflar el diseño del ítem
        return RocketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = rockets[position]
        holder.nameTextView.text = rocket.name
        holder.descriptionTextView.text = rocket.description

        // Configurar el evento de clic en el ítem
        holder.itemView.setOnClickListener {
            onRocketClicked(rocket) // Llamar al callback con el cohete seleccionado
        }
    }

    override fun getItemCount(): Int = rockets.size
}
