package com.example.practicaretrofit.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.practicaretrofit.databinding.ActivityPersonaListBinding
import com.example.practicaretrofit.models.Persona
import com.example.practicaretrofit.models.PersonaDeleteResponse
import com.example.practicaretrofit.repositories.PersonaDeleteListener
import com.example.practicaretrofit.repositories.PersonaListListener
import com.example.practicaretrofit.repositories.PersonaRepository
import com.example.practicaretrofit.ui.adapters.PersonaAdapter

class PersonaListActivity : AppCompatActivity(), PersonaAdapter.PersonaEventListener,
    PersonaListListener, PersonaDeleteListener {
    private lateinit var binding: ActivityPersonaListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonaListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.fabAddPersona.setOnClickListener {
            val intent = Intent(this, PersonaDetailActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        PersonaRepository.fetchListaPersonas(this)
    }

    private fun setupRecyclerView() {
        val dividerItemDecoration =
            DividerItemDecoration(binding.lstPersonas.context, DividerItemDecoration.VERTICAL)
        binding.lstPersonas.apply {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@PersonaListActivity).apply {
                    orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                }
            adapter = PersonaAdapter(emptyList(), this@PersonaListActivity)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onPersonaClick(persona: Persona) {
        val intent = Intent(this, PersonaDetailActivity::class.java)
        intent.putExtra("id", persona.id)
        startActivity(intent)
    }

    override fun onPersonaDeleteClick(persona: Persona) {
        PersonaRepository.deletePersona(persona, this)
    }


    //#region PersonaListListener
    override fun onPersonaListFetched(personas: List<Persona>) {
        binding.lstPersonas.adapter.let { adapter ->
            if (adapter is PersonaAdapter) {
                adapter.updateList(personas)
            }
        }
    }

    override fun onPersonaListFetchError(t: Throwable) {
        t.printStackTrace()
    }
    //#endregion
    //#region PersonaDeleteListener

    override fun onPersonaDeleteSuccess(respuesta: PersonaDeleteResponse) {
        if (respuesta.res == "success") {
            PersonaRepository.fetchListaPersonas(this@PersonaListActivity)
        } else {
            Toast.makeText(
                this@PersonaListActivity,
                respuesta.reason,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onPersonaDeleteError(t: Throwable) {
        t.printStackTrace()
    }
    //#endregion
}