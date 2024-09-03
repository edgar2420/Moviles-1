package com.example.practicaretrofit.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaretrofit.databinding.ActivityPersonaDetailBinding
import com.example.practicaretrofit.models.Persona
import com.example.practicaretrofit.repositories.PersonaDetailListener
import com.example.practicaretrofit.repositories.PersonaInsertListener
import com.example.practicaretrofit.repositories.PersonaRepository
import com.example.practicaretrofit.repositories.PersonaUpdateListener

class PersonaDetailActivity : AppCompatActivity(), PersonaDetailListener, PersonaInsertListener,
    PersonaUpdateListener {
    private var id: Int = 0
    private lateinit var binding: ActivityPersonaDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id", 0)
        if (id != 0) {
            PersonaRepository.fetchPersonaDetail(id, this)
        }
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnCancel.setOnClickListener { finish() }
        binding.btnSave.setOnClickListener { savePersona() }
    }

    private fun savePersona() {
        val nombres = binding.txtName.text.toString()
        val apellidos = binding.txtLastName.text.toString()
        val edad = binding.txtAge.text.toString().toInt()
        val ciudad = binding.txtCity.text.toString()
        val fechaNacimiento = binding.txtBirthDate.text.toString()


        if (id == 0) {
            PersonaRepository.saveInsertPersona(
                nombres,
                apellidos,
                edad,
                ciudad,
                fechaNacimiento,
                this
            )
        } else {
            PersonaRepository.saveUpdatePersona(
                id,
                nombres,
                apellidos,
                edad,
                ciudad,
                fechaNacimiento,
                this
            )
        }
    }


    private fun loadPersonaInForm(persona: Persona?) {
        binding.txtName.setText(persona?.nombres)
        binding.txtLastName.setText(persona?.apellidos)
        binding.txtAge.setText(persona?.edad.toString())
        binding.txtCity.setText(persona?.ciudad)
        binding.txtBirthDate.setText(persona?.fechaNacimiento)
    }

    //#region PersonaDetailListener
    override fun onPersonaDetailFetched(persona: Persona) {
        loadPersonaInForm(persona)
    }

    override fun onPersonaDetailFetchError(t: Throwable) {
        t.printStackTrace()
    }

    //#endregion
    //#region PersonaInsertListener
    override fun onPersonaInsertSuccess(persona: Persona) {
        finish()
    }

    override fun onPersonaInsertError(t: Throwable) {
        t.printStackTrace()
    }
    //#endregion
    //#region PersonaUpdateListener

    override fun onPersonaUpdateSuccess(persona: Persona) {
        finish()
    }

    override fun onPersonaUpdateError(t: Throwable) {
        t.printStackTrace()
    }
    //#endregion
}