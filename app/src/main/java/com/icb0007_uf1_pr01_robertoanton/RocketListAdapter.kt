package com.icb0007_uf1_pr01_robertoanton

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador para el RecyclerView que muestra la lista de cohetes
class RocketListAdapter(
    private val rockets: List<String>, // Lista de nombres de cohetes
    private val onRocketClick: (String) -> Unit // Callback para manejar el clic en un cohete
) : RecyclerView.Adapter<RocketListAdapter.RocketViewHolder>() {

    // ViewHolder para representar un elemento individual de la lista
    class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rocketNameTextView: TextView = itemView.findViewById(R.id.textViewRocketName)
    }

    // Crear una nueva vista para cada elemento de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rocket, parent, false) // Inflar el diseño del elemento
        return RocketViewHolder(view)
    }

    // Vincular los datos (nombre del cohete) a la vista correspondiente
    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocketName = rockets[position] // Obtener el nombre del cohete
        holder.rocketNameTextView.text = rocketName // Establecer el texto del TextView

        // Configurar el clic para navegar al fragmento de detalles
        holder.itemView.setOnClickListener {
            onRocketClick(rocketName) // Llamar al callback con el nombre del cohete
        }
    }

    // Retornar el número total de elementos en la lista
    override fun getItemCount(): Int {
        return rockets.size
    }
}
