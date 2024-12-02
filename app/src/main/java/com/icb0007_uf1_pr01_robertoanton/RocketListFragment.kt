package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
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
    private val rockets = mutableListOf<Rocket>() // Lista de cohetes en memoria

    // Acceso al repositorio para interactuar con la base de datos
    private lateinit var rocketRepository: RocketRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Indicar que este fragmento tiene un menú
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)

        // Inicializar el repositorio con acceso a la base de datos
        val database = AppDatabase.getDatabase(requireContext())
        rocketRepository = RocketRepository(database.rocketDao())

        // Configurar el RecyclerView para mostrar la lista de cohetes
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        rocketListAdapter = RocketListAdapter(rockets) { rocket ->
            // Navegar al fragmento de detalles pasando el cohete seleccionado
            val action = RocketListFragmentDirections
                .actionRocketListFragmentToRocketDetailFragment(rocket)
            findNavController().navigate(action)
        }
        recyclerView.adapter = rocketListAdapter

        // Cargar los datos desde la base de datos o la API
        loadRockets()

        return view
    }

    override fun onResume() {
        super.onResume()
        // Forzar la recarga de la lista al volver a este fragmento
        loadRockets()
        Log.d("RocketList", "Lista recargada en onResume.")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_rocket_list, menu) // Inflar el archivo XML del menú
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.menu_logout -> {
                // Acción al pulsar "Cerrar Sesión" desde el menú
                performLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Método para cargar los datos de los cohetes.
     */
    private fun loadRockets() {
        lifecycleScope.launch {
            try {
                // Obtener los cohetes desde la base de datos local
                val localRockets = rocketRepository.getAllRockets()
                if (localRockets.isNotEmpty()) {
                    rockets.clear()
                    rockets.addAll(localRockets.map { it.toRocket() })
                    withContext(Dispatchers.Main) {
                        rocketListAdapter.notifyDataSetChanged()
                        Log.d("RocketList", "Datos cargados desde la base de datos")
                    }
                } else {
                    // Si no hay cohetes locales, obtenerlos de la API
                    fetchRocketsFromApi()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * Método para obtener los cohetes desde la API.
     */
    private fun fetchRocketsFromApi() {
        val retrofit = RetrofitInstance.getRetrofitInstance()
        val service = retrofit.create(SpaceXApiService::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = service.getRockets()
                withContext(Dispatchers.Main) {
                    rockets.clear()
                    rockets.addAll(response)
                    rocketListAdapter.notifyDataSetChanged()
                    saveRocketsToLocalDatabase(response)
                    Log.d("RocketList", "Datos cargados desde la API")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "Error al obtener los cohetes: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    /**
     * Guardar los cohetes obtenidos desde la API en la base de datos local.
     */
    private suspend fun saveRocketsToLocalDatabase(rockets: List<Rocket>) {
        val rocketEntities = rockets.map { it.toEntity() }
        rocketRepository.insertAllRockets(rocketEntities)
    }

    /**
     * Método para cerrar sesión y navegar al fragmento de inicio de sesión.
     */
    private fun performLogout() {
        try {
            Log.d("RocketListFragment", "Cerrando sesión y limpiando pila...")
            Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show()

            // Crear un nuevo grafo y navegar al fragmento de inicio de sesión
            findNavController().apply {
                popBackStack(R.id.rocketListFragment, true)
                navigate(R.id.loginFragment)
            }
        } catch (e: Exception) {
            Log.e("RocketListFragment", "Error al intentar cerrar sesión: ${e.message}")
        }
    }
}
