package com.example.apis.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apis.R
import com.example.apis.api.PersonaService
import com.example.apis.models.Persona
import com.example.apis.models.PersonaDeleteResponse
import com.example.apis.ui.adapters.PersonaAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), PersonaAdapter.OnPersonaClickListener {
    private lateinit var ltsPersonas : RecyclerView
    private lateinit var btnCreatePersona : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ltsPersonas = findViewById(R.id.recycler)
        btnCreatePersona = findViewById(R.id.fabCreatePersona)
        setupListeners()
        setupRecycler()
    }

    private fun setupListeners() {
        btnCreatePersona.setOnClickListener {
            val intent = Intent(this, PersonaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchPersonas()
    }

    private fun fetchPersonas() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://personas.jmacboy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val personaService = retrofit.create(PersonaService::class.java)
        personaService.getPersonas().enqueue(object : Callback<List<Persona>> {
            override fun onResponse(call: Call<List<Persona>>, response: Response<List<Persona>>) {
                if (response.isSuccessful) {
                    val personas = response.body()

                    ltsPersonas.adapter.let { adapter ->
                        if (adapter is PersonaAdapter) {
                            if (personas != null) {
                                adapter.updateList(personas)
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Persona>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun setupRecycler() {
        var dividerItemDecoration = DividerItemDecoration(ltsPersonas.context, DividerItemDecoration.VERTICAL)
        ltsPersonas.apply {
            layoutManager = LinearLayoutManager(this@MainActivity).apply{
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = PersonaAdapter(emptyList(), this@MainActivity)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onPersonaClick(persona: Persona) {
        val intent = Intent(this, PersonaActivity::class.java)
        intent.putExtra("id", persona.id)
        startActivity(intent)
    }

    override fun onPersonaDelete(persona: Persona) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://personas.jmacboy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val personaService = retrofit.create(PersonaService::class.java)
        personaService.deletePersonaById(persona.id).enqueue(object : Callback<PersonaDeleteResponse> {
            override fun onResponse(call: Call<PersonaDeleteResponse>, response: Response<PersonaDeleteResponse>) {
                if (response.isSuccessful) {
                    val respuesta = response.body()
                    if(respuesta?.res == "success"){
                        fetchPersonas()
                    }else{
                        Toast.makeText(this@MainActivity, respuesta?.reason, Toast.LENGTH_SHORT).show()
                    }

                }
            }
            override fun onFailure(call: Call<PersonaDeleteResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}