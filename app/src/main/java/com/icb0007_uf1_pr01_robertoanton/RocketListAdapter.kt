package com.icb0007_uf1_pr01_robertoanton

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        val wikipediaButton: Button = itemView.findViewById(R.id.buttonWikipedia)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rocket, parent, false)
        return RocketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = rockets[position]
        holder.nameTextView.text = rocket.name
        holder.descriptionTextView.text = rocket.description

        // Verificar si el enlace de Wikipedia es genérico o válido
        val genericWikipediaUrl = "https://es.wikipedia.org/"
        if (rocket.wikipedia == genericWikipediaUrl || rocket.wikipedia.isNullOrBlank()) {
            holder.wikipediaButton.text = "No disponible"
            holder.wikipediaButton.isEnabled = false
            holder.wikipediaButton.setBackgroundColor(holder.itemView.context.getColor(R.color.gray)) // Cambiar color a gris
        } else {
            holder.wikipediaButton.text = "Ver en Wikipedia"
            holder.wikipediaButton.isEnabled = true
            holder.wikipediaButton.setBackgroundColor(holder.itemView.context.getColor(R.color.purple_500)) // Color morado
            holder.wikipediaButton.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(rocket.wikipedia))
                context.startActivity(intent) // Abrir el enlace en el navegador
            }
        }

        // Configurar clic en el ítem de la lista
        holder.itemView.setOnClickListener {
            onRocketClicked(rocket)
        }
    }

    override fun getItemCount(): Int = rockets.size
}
