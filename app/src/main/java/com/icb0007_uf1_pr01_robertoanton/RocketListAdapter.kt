package com.icb0007_uf1_pr01_robertoanton

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador para manejar y mostrar la lista de cohetes en el RecyclerView
class RocketListAdapter(
    private val rockets: List<Rocket>, // Lista de cohetes a mostrar
    private val onRocketClicked: (Rocket) -> Unit // Callback para manejar clics en ítems
) : RecyclerView.Adapter<RocketListAdapter.RocketViewHolder>() {

    // ViewHolder que maneja las vistas de cada ítem del RecyclerView
    class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewRocketName) // Nombre del cohete
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewRocketDescription) // Descripción del cohete
        val wikipediaButton: Button = itemView.findViewById(R.id.buttonWikipedia) // Botón de Wikipedia
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        // Inflar el diseño de cada ítem en el RecyclerView
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rocket, parent, false)
        return RocketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = rockets[position]

        // Configurar el nombre del cohete
        holder.nameTextView.text = rocket.name

        // Configurar la descripción, asegurándonos de que se ajuste bien al tamaño de la pantalla
        holder.descriptionTextView.text = rocket.description

        // Configurar el botón de Wikipedia
        val genericWikipediaUrl = "https://es.wikipedia.org/"
        if (rocket.wikipedia == genericWikipediaUrl || rocket.wikipedia.isNullOrBlank()) {
            // Caso donde no hay un enlace válido
            holder.wikipediaButton.text = "No disponible"
            holder.wikipediaButton.isEnabled = false
            holder.wikipediaButton.setBackgroundColor(holder.itemView.context.getColor(R.color.gray)) // Cambiar color a gris
        } else {
            // Caso donde el enlace es válido
            holder.wikipediaButton.text = "Ver en Wikipedia"
            holder.wikipediaButton.isEnabled = true
            holder.wikipediaButton.setBackgroundColor(holder.itemView.context.getColor(R.color.purple_500)) // Color morado
            holder.wikipediaButton.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(rocket.wikipedia))
                context.startActivity(intent) // Abrir el enlace en el navegador
            }
        }

        // Configurar el clic en el ítem completo para manejar detalles del cohete
        holder.itemView.setOnClickListener {
            onRocketClicked(rocket)
        }
    }

    override fun getItemCount(): Int = rockets.size // Tamaño de la lista de cohetes
}
