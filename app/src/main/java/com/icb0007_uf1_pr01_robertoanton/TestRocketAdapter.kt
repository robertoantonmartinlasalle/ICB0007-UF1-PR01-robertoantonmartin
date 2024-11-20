package com.icb0007_uf1_pr01_robertoanton

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador para mostrar la lista de cohetes en el RecyclerView
class TestRocketAdapter(
    private val rockets: List<Rocket>
) : RecyclerView.Adapter<TestRocketAdapter.RocketViewHolder>() {

    // ViewHolder que representa un elemento de la lista
    class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rocketName: TextView = itemView.findViewById(R.id.textViewRocketName)
        val firstFlight: TextView = itemView.findViewById(R.id.firstFlight)
        val costPerLaunch: TextView = itemView.findViewById(R.id.costPerLaunch)
        val wikipediaButton: Button = itemView.findViewById(R.id.wikipediaButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rocket, parent, false)
        return RocketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = rockets[position]
        holder.rocketName.text = rocket.name
        holder.firstFlight.text = "First Flight: ${rocket.first_flight}"
        holder.costPerLaunch.text = "Cost per launch: $${rocket.cost_per_launch}"

        holder.wikipediaButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(rocket.wikipedia))
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = rockets.size
}
