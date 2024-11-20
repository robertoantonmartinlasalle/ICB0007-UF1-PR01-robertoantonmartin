package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

// Fragmento para mostrar los detalles de un cohete
class RocketDetailFragment : Fragment() {

    // Argumentos recibidos desde RocketListFragment usando SafeArgs
    private val args: RocketDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_rocket_detail, container, false)

        // Mostrar el nombre del cohete recibido en el argumento
        val rocketNameTextView = view.findViewById<TextView>(R.id.textViewRocketDetailName)
        rocketNameTextView.text = args.rocketName // Mostrar el nombre del cohete

        return view
    }
}
