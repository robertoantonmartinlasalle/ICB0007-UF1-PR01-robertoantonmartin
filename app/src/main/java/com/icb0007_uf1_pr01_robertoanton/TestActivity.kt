package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Actividad de prueba para mostrar la lista de cohetes
class TestActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.recyclerViewRockets)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener los cohetes desde la API
        fetchRockets()
    }

    private fun fetchRockets() {
        RetrofitInstance.api.getRockets().enqueue(object : Callback<List<Rocket>> {
            override fun onResponse(call: Call<List<Rocket>>, response: Response<List<Rocket>>) {
                if (response.isSuccessful) {
                    response.body()?.let { rockets ->
                        // Pasar los cohetes al adaptador
                        recyclerView.adapter = TestRocketAdapter(rockets)
                    }
                } else {
                    Log.e("TestActivity", "Error en la respuesta: ${response.errorBody()?.string()}")
                    Toast.makeText(this@TestActivity, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Rocket>>, t: Throwable) {
                Log.e("TestActivity", "Error de red: ${t.message}")
                Toast.makeText(this@TestActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
