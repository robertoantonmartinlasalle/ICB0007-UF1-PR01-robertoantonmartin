package com.icb0007_uf1_pr01_robertoanton

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RocketListAdapter(private val rockets: List<Rocket>) :
    RecyclerView.Adapter<RocketListAdapter.RocketViewHolder>() {

    // ViewHolder para manejar las vistas de cada item
    class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewRocketName)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewRocketDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rocket, parent, false) // Inflar diseño del item
        return RocketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = rockets[position]

        // Configurar los datos en las vistas
        holder.nameTextView.text = rocket.name
        holder.descriptionTextView.text = rocket.description
    }

    override fun getItemCount(): Int {
        return rockets.size
    }
}
