package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/* Conforme fui avanzando la app tuve que realizar varias modificaciones para que fuera completamente
funcional
*/
class RocketListFragment : Fragment() {

    private lateinit var rocketListAdapter: RocketListAdapter
    private val rockets = mutableListOf<Rocket>() // Lista original de cohetes
    private val filteredRockets = mutableListOf<Rocket>() // Lista filtrada de cohetes

    private lateinit var rocketRepository: RocketRepository // Repositorio de la base de datos

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)

        // Inicializar el repositorio
        val database = AppDatabase.getDatabase(requireContext())
        rocketRepository = RocketRepository(database.rocketDao())

        // Configurar RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        rocketListAdapter = RocketListAdapter(filteredRockets) { rocket ->
            handleRocketClick(rocket)
        }
        recyclerView.adapter = rocketListAdapter

        // Cargar los datos al iniciar la vista
        loadRockets()

        // Configurar el menú usando MenuHost y MenuProvider
        setupMenu()

        return view
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity() // Obtiene el host del menú
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Inflar el menú en la barra de herramientas
                menuInflater.inflate(R.menu.menu_rocket_list, menu)

                // Configurar el SearchView para el menú de búsqueda
                val searchItem = menu.findItem(R.id.menu_search)
                val searchView = searchItem.actionView as SearchView
                searchView.queryHint = "Buscar cohetes..."

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        filterRockets(query)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        filterRockets(newText)
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_logout -> {
                        performLogout()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED) // El menú se asocia al Lifecycle del Fragment
    }

    private fun handleRocketClick(rocket: Rocket) {
        val detailsView = view?.findViewById<View>(R.id.detailsView)
        if (detailsView != null) {
            // Mostrar detalles en pantalla dividida sin botones
            val fragment = RocketDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("rocket", rocket)
                    putBoolean("showButtons", false)
                }
            }
            parentFragmentManager.commit {
                replace(R.id.detailsView, fragment)
                setReorderingAllowed(true)
            }
        } else {
            // Navegación estándar en modo portrait
            val action = RocketListFragmentDirections
                .actionRocketListFragmentToRocketDetailFragment(rocket)
            findNavController().navigate(action)
        }
    }

    private fun loadRockets() {
        lifecycleScope.launch {
            try {
                val localRockets = rocketRepository.getAllRockets()
                withContext(Dispatchers.Main) {
                    rockets.clear()
                    rockets.addAll(localRockets.map { it.toRocket() })
                    filteredRockets.clear()
                    filteredRockets.addAll(rockets)
                    rocketListAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun filterRockets(query: String?) {
        filteredRockets.clear()
        if (query.isNullOrEmpty()) {
            filteredRockets.addAll(rockets)
        } else {
            filteredRockets.addAll(
                rockets.filter { rocket ->
                    rocket.name.contains(query, ignoreCase = true) ||
                            rocket.description.contains(query, ignoreCase = true)
                }
            )
        }
        rocketListAdapter.notifyDataSetChanged()
    }

    private fun performLogout() {
        try {
            Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show()
            findNavController().apply {
                popBackStack(R.id.rocketListFragment, true)
                navigate(R.id.loginFragment)
            }
        } catch (e: Exception) {
            Log.e("RocketListFragment", "Error al intentar cerrar sesión: ${e.message}")
        }
    }
}
