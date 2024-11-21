package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class RocketDetailFragment : Fragment() {

    private val args: RocketDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rocket_detail, container, false)

        val rocket = args.rocket

        view.findViewById<TextView>(R.id.textViewRocketDetailName).text = rocket.name
        view.findViewById<TextView>(R.id.textViewRocketType).text = "Type: ${rocket.type}"
        view.findViewById<TextView>(R.id.textViewRocketCountry).text = "Country: ${rocket.country}"
        view.findViewById<TextView>(R.id.textViewRocketCompany).text = "Company: ${rocket.company}"
        view.findViewById<TextView>(R.id.textViewRocketSuccessRate).text =
            "Success Rate: ${rocket.success_rate_pct}%"
        view.findViewById<TextView>(R.id.textViewRocketCost).text =
            "Cost per launch: $${rocket.cost_per_launch}"
        view.findViewById<TextView>(R.id.textViewRocketHeight).text =
            "Height: ${rocket.height.meters} m / ${rocket.height.feet} ft"
        view.findViewById<TextView>(R.id.textViewRocketDiameter).text =
            "Diameter: ${rocket.diameter.meters} m / ${rocket.diameter.feet} ft"

        return view
    }
}

