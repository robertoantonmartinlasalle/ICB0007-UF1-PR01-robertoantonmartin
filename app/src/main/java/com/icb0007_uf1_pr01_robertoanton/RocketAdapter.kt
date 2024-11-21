package com.icb0007_uf1_pr01_robertoanton

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador para mostrar la lista de cohetes en un RecyclerView
class RocketAdapter(
    private val rockets: List<Rocket>, // Lista de cohetes
    private val onRocketClick: (Rocket) -> Unit // Callback para manejar el clic en un cohete
) : RecyclerView.Adapter<RocketAdapter.RocketViewHolder>() {

    // ViewHolder para representar cada elemento individual de la lista
    class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rocketName: TextView = itemView.findViewById(R.id.textViewRocketName) // Nombre del cohete
        val rocketDescription: TextView = itemView.findViewById(R.id.description) // Descripción del cohete
        val wikipediaButton: Button = itemView.findViewById(R.id.wikipediaButton) // Botón de Wikipedia
    }

    // Crear una nueva vista para un elemento del RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rocket, parent, false) // Inflar el diseño del elemento
        return RocketViewHolder(view)
    }

    // Vincular los datos del cohete a las vistas en el ViewHolder
    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = rockets[position] // Obtener el cohete en la posición actual
        holder.rocketName.text = rocket.name // Establecer el nombre del cohete
        holder.rocketDescription.text = rocket.description // Establecer la descripción del cohete

        // Configurar el clic en el botón de Wikipedia
        holder.wikipediaButton.setOnClickListener {
            val context = it.context
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(rocket.wikipedia)) // Crear un Intent para abrir el enlace
            context.startActivity(intent) // Iniciar el Intent
        }

        // Configurar el clic en el elemento para mostrar los detalles del cohete
        holder.itemView.setOnClickListener {
            onRocketClick(rocket) // Llamar al callback con el cohete seleccionado
        }
    }

    // Obtener el número total de elementos en la lista
    override fun getItemCount(): Int = rockets.size
}
