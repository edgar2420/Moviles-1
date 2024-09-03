package com.example.apis.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apis.api.PersonaService
import com.example.apis.databinding.ActivityPersonaBinding
import com.example.apis.models.Persona
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonaBinding
    private var personaId : Long = 0
    private lateinit var persona : Persona

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        personaId = intent.getLongExtra("id",0)
        if (personaId != 0L) {
            fetchPersona()
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            updatePersona()
        }
        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }

    private fun setupTexts() {
        binding.id.setText("ID : "+personaId.toString())
        binding.txtNombres.setText(persona.nombres)
        binding.txtApellidos.setText(persona.apellidos)
        binding.txtEdad.setText(persona.edad.toString())
        binding.txtCiudad.setText(persona.ciudad)
        binding.txtFechaNacimiento.setText(persona.fechaNacimiento)
    }

    private fun updatePersona() {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("nombres", binding.txtNombres.text.toString())
            .addFormDataPart("apellidos", binding.txtApellidos.text.toString())
            .addFormDataPart("edad", binding.txtEdad.text.toString())
            .addFormDataPart("ciudad", binding.txtCiudad.text.toString())
            .addFormDataPart("fechaNacimiento", binding.txtFechaNacimiento.text.toString())
            .build()

        if (personaId.toString() == "0"){
            saveAddPersona(body)
        }else{
            saveUpdatePersona(body)
        }
    }

    private fun saveUpdatePersona(body: MultipartBody) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://personas.jmacboy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val personaService = retrofit.create(PersonaService::class.java)
        personaService.updatePersonaById(personaId,body).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    finish()
                }
            }
            override fun onFailure(call: Call<Persona>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun saveAddPersona(body: MultipartBody) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://personas.jmacboy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val personaService = retrofit.create(PersonaService::class.java)
        personaService.createPersona(body).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    finish()
                }
            }
            override fun onFailure(call: Call<Persona>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun fetchPersona() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://personas.jmacboy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val personaService = retrofit.create(PersonaService::class.java)
        personaService.getPersonaById(personaId).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val personaAux = response.body()
                    if (personaAux != null) {
                        persona = personaAux
                        setupTexts()
                    }
                }
            }
            override fun onFailure(call: Call<Persona>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}