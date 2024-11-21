package com.icb0007_uf1_pr01_robertoanton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewRockets)
        recyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitInstance.api.getRockets().enqueue(object : Callback<List<Rocket>> {
            override fun onResponse(
                call: Call<List<Rocket>>,
                response: Response<List<Rocket>>
            ) {
                val rockets = response.body()
                if (rockets != null) {
                    recyclerView.adapter = TestRocketAdapter(rockets)
                }
            }

            override fun onFailure(call: Call<List<Rocket>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
