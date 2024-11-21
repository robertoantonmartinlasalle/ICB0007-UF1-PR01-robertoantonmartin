package com.icb0007_uf1_pr01_robertoanton

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador para mostrar la lista completa de cohetes en el RecyclerView
class TestRocketAdapter(
    private val rockets: List<Rocket>
) : RecyclerView.Adapter<TestRocketAdapter.RocketViewHolder>() {

    // ViewHolder que representa un elemento de la lista
    class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rocketName: TextView = itemView.findViewById(R.id.textViewRocketName)
        val rocketType: TextView = itemView.findViewById(R.id.rocketType)
        val successRate: TextView = itemView.findViewById(R.id.successRate)
        val costPerLaunch: TextView = itemView.findViewById(R.id.costPerLaunch)
        val country: TextView = itemView.findViewById(R.id.country)
        val company: TextView = itemView.findViewById(R.id.company)
        val description: TextView = itemView.findViewById(R.id.description)
        val height: TextView = itemView.findViewById(R.id.height)
        val diameter: TextView = itemView.findViewById(R.id.diameter)
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
        holder.rocketType.text = "Type: ${rocket.type}"
        holder.successRate.text = "Success Rate: ${rocket.success_rate_pct}%"
        holder.costPerLaunch.text = "Cost per launch: $${rocket.cost_per_launch}"
        holder.country.text = "Country: ${rocket.country}"
        holder.company.text = "Company: ${rocket.company}"
        holder.description.text = rocket.description
        holder.height.text = "Height: ${rocket.height.meters} m / ${rocket.height.feet} ft"
        holder.diameter.text = "Diameter: ${rocket.diameter.meters} m / ${rocket.diameter.feet} ft"

        holder.wikipediaButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(rocket.wikipedia))
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = rockets.size
}
