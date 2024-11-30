package com.icb0007_uf1_pr01_robertoanton

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador para mostrar la lista de cohetes en un RecyclerView.
class RocketListAdapter(private val rockets: List<Rocket>) :
    RecyclerView.Adapter<RocketListAdapter.RocketViewHolder>() {

    /**
     * ViewHolder es una clase interna que mantiene referencias a las vistas
     * de cada elemento del RecyclerView.
     */
    class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referencia al TextView que muestra el nombre del cohete.
        val nameTextView: TextView = itemView.findViewById(R.id.textViewRocketName)
        // Referencia al TextView que muestra la descripción del cohete.
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewRocketDescription)
    }

    /**
     * Este método se llama cuando se necesita crear un nuevo ViewHolder.
     * Infla el diseño del elemento de la lista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        // Infla el diseño del item (R.layout.item_rocket).
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rocket, parent, false)
        return RocketViewHolder(view)
    }

    /**
     * Este método se llama para vincular los datos de un elemento de la lista al ViewHolder.
     * @param holder El ViewHolder en el que se configurarán los datos.
     * @param position La posición del elemento en la lista.
     */
    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = rockets[position] // Obtiene el cohete actual de la lista.

        // Configura el texto de las vistas con los datos del cohete.
        holder.nameTextView.text = rocket.name
        holder.descriptionTextView.text = rocket.description
    }

    /**
     * Este método retorna el número de elementos en la lista.
     * @return El tamaño de la lista de cohetes.
     */
    override fun getItemCount(): Int {
        return rockets.size
    }
}
