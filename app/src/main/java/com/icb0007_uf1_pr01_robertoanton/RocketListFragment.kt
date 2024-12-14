package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RocketListFragment : Fragment() {

    private lateinit var rocketListAdapter: RocketListAdapter
    private val rockets = mutableListOf<Rocket>() // Lista original de cohetes
    private val filteredRockets = mutableListOf<Rocket>() // Lista filtrada de cohetes

    private lateinit var rocketRepository: RocketRepository // Repositorio de la base de datos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) // Indicar que este fragmento tiene un menú
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)

        // Inicializar el repositorio
        val database = AppDatabase.getDatabase(requireContext())
        rocketRepository = RocketRepository(database.rocketDao())

        // Configurar RecyclerView con un layout vertical
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        rocketListAdapter = RocketListAdapter(filteredRockets) { rocket ->
            // Navegar al fragmento de detalles del cohete
            val action = RocketListFragmentDirections
                .actionRocketListFragmentToRocketDetailFragment(rocket)
            findNavController().navigate(action)
        }
        recyclerView.adapter = rocketListAdapter

        // Cargar los datos al iniciar la vista
        loadRockets()

        return view
    }

    override fun onResume() {
        super.onResume()
        loadRockets() // Recargar la lista al reanudar el fragmento
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_rocket_list, menu)

        // Configurar SearchView para filtrar cohetes
        val searchItem = menu.findItem(R.id.menu_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Buscar cohetes..." // Mensaje de ayuda en el campo de búsqueda

        // Listener para manejar el texto introducido por el usuario
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterRockets(query) // Filtrar cohetes al enviar texto
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterRockets(newText) // Filtrar cohetes mientras el usuario escribe
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Cerrar sesión al seleccionar la opción en el menú
            R.id.menu_logout -> {
                performLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Cargar los datos de cohetes desde la base de datos local o la API.
     */
    private fun loadRockets() {
        lifecycleScope.launch {
            try {
                val localRockets = rocketRepository.getAllRockets() // Obtener cohetes locales
                withContext(Dispatchers.Main) {
                    rockets.clear()
                    rockets.addAll(localRockets.map { it.toRocket() })
                    filteredRockets.clear()
                    filteredRockets.addAll(rockets) // Inicializar lista filtrada con todos los cohetes
                    rocketListAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                // Mostrar un mensaje de error si falla la carga
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * Filtrar la lista de cohetes según el texto introducido.
     */
    private fun filterRockets(query: String?) {
        filteredRockets.clear()
        if (query.isNullOrEmpty()) {
            // Si el texto está vacío, mostrar todos los cohetes
            filteredRockets.addAll(rockets)
        } else {
            // Filtrar cohetes que coincidan con el nombre o descripción
            filteredRockets.addAll(
                rockets.filter { rocket ->
                    rocket.name.contains(query, ignoreCase = true) ||
                            rocket.description.contains(query, ignoreCase = true)
                }
            )
        }
        rocketListAdapter.notifyDataSetChanged()
    }

    /**
     * Manejar el cierre de sesión y redirigir al fragmento de inicio de sesión.
     */
    private fun performLogout() {
        try {
            Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show()
            findNavController().apply {
                popBackStack(R.id.rocketListFragment, true) // Limpiar pila de navegación
                navigate(R.id.loginFragment) // Navegar al fragmento de inicio de sesión
            }
        } catch (e: Exception) {
            Log.e("RocketListFragment", "Error al intentar cerrar sesión: ${e.message}")
        }
    }
}
