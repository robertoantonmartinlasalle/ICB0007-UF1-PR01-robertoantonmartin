package com.icb0007_uf1_pr01_robertoanton

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Clase adaptador para el RecyclerView que se utiliza en RocketListFragment.
// Este adaptador se encarga de enlazar los datos (lista de nombres de cohetes) con las vistas del RecyclerView.
class RocketListAdapter(private val rockets: List<String>) :
    RecyclerView.Adapter<RocketListAdapter.RocketViewHolder>() {

    // Clase ViewHolder que representa un elemento individual en el RecyclerView.
    // Su responsabilidad es mantener referencias a las vistas de un elemento y
    // permitir que el adaptador las reutilice cuando sea posible para mejorar el rendimiento.
    class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referencia al TextView dentro del diseño item_rocket.xml donde se mostrará el nombre del cohete.
        val rocketNameTextView: TextView = itemView.findViewById(R.id.textViewRocketName)
    }

    // Método que se llama cuando se necesita crear un nuevo ViewHolder.
    // Esto ocurre cuando el RecyclerView necesita una nueva vista para mostrar un elemento.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        // Inflamos el diseño del elemento (item_rocket.xml) para crear la vista de cada cohete.
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rocket, parent, false)
        // Retornamos un nuevo RocketViewHolder que envuelve la vista inflada.
        return RocketViewHolder(view)
    }

    // Método que se llama para enlazar los datos de un elemento con las vistas del ViewHolder.
    // Esto ocurre cuando el RecyclerView está a punto de mostrar un elemento en una posición específica.
    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        // Establecemos el texto del TextView con el nombre del cohete correspondiente a la posición actual.
        holder.rocketNameTextView.text = rockets[position]
    }

    // Método que devuelve la cantidad de elementos en la lista de datos.
    // Esto le dice al RecyclerView cuántos elementos debe mostrar.
    override fun getItemCount(): Int {
        return rockets.size
    }
}
