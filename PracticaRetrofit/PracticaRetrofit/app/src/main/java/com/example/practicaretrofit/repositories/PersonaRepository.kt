package com.example.practicaretrofit.repositories

import com.example.practicaretrofit.api.PersonasService
import com.example.practicaretrofit.models.Persona
import com.example.practicaretrofit.models.PersonaDeleteResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PersonaRepository {

    fun fetchListaPersonas(listener: PersonaListListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val personaService = retrofit.create(PersonasService::class.java)
        personaService.getPersonasList().enqueue(object : Callback<List<Persona>> {
            override fun onResponse(call: Call<List<Persona>>, response: Response<List<Persona>>) {
                if (response.isSuccessful) {
                    val personas = response.body()
                    personas?.let {
                        listener.onPersonaListFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Persona>>, t: Throwable) {
                listener.onPersonaListFetchError(t)
            }
        })

    }

    fun deletePersona(persona: Persona, listener: PersonaDeleteListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val personaService = retrofit.create(PersonasService::class.java)
        personaService.deletePersona(persona.id).enqueue(object : Callback<PersonaDeleteResponse> {
            override fun onResponse(
                call: Call<PersonaDeleteResponse>,
                response: Response<PersonaDeleteResponse>
            ) {
                if (response.isSuccessful) {
                    val respuesta = response.body()
                    respuesta?.let { listener.onPersonaDeleteSuccess(it) }
                }
            }

            override fun onFailure(call: Call<PersonaDeleteResponse>, t: Throwable) {
                listener.onPersonaDeleteError(t)
            }
        })
    }

    fun fetchPersonaDetail(id: Int, listener: PersonaDetailListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val personaService = retrofit.create(PersonasService::class.java)
        personaService.getPersonasById(id).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val persona = response.body()
                    persona?.let {
                        listener.onPersonaDetailFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                listener.onPersonaDetailFetchError(t)
            }
        })
    }

    fun saveInsertPersona(
        nombres: String,
        apellidos: String,
        edad: Int,
        ciudad: String,
        fechaNacimiento: String,
        listener: PersonaInsertListener
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body = createPostBody(nombres, apellidos, edad, ciudad, fechaNacimiento)

        val personaService = retrofit.create(PersonasService::class.java)
        personaService.insertPersona(body).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val persona = response.body()
                    persona?.let {
                        listener.onPersonaInsertSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                listener.onPersonaInsertError(t)
            }
        })
    }


    fun saveUpdatePersona(
        id: Int,
        nombres: String,
        apellidos: String,
        edad: Int,
        ciudad: String,
        fechaNacimiento: String,
        listener: PersonaUpdateListener
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body = createPostBody(nombres, apellidos, edad, ciudad, fechaNacimiento)
        val personaService = retrofit.create(PersonasService::class.java)
        personaService.editPersona(id, body).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val persona = response.body()
                    persona?.let {
                        listener.onPersonaUpdateSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                listener.onPersonaUpdateError(t)
            }
        })
    }

    private fun createPostBody(
        nombres: String,
        apellidos: String,
        edad: Int,
        ciudad: String,
        fechaNacimiento: String
    ): RequestBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("nombres", nombres)
            .addFormDataPart("apellidos", apellidos)
            .addFormDataPart("edad", edad.toString())
            .addFormDataPart("ciudad", ciudad)
            .addFormDataPart("fechaNacimiento", fechaNacimiento)
            .build()
    }
}

interface PersonaListListener {
    fun onPersonaListFetched(personas: List<Persona>)
    fun onPersonaListFetchError(t: Throwable)
}

interface PersonaDeleteListener {
    fun onPersonaDeleteSuccess(response: PersonaDeleteResponse)
    fun onPersonaDeleteError(t: Throwable)
}

interface PersonaDetailListener {
    fun onPersonaDetailFetched(persona: Persona)
    fun onPersonaDetailFetchError(t: Throwable)
}

interface PersonaInsertListener {
    fun onPersonaInsertSuccess(persona: Persona)
    fun onPersonaInsertError(t: Throwable)
}

interface PersonaUpdateListener {
    fun onPersonaUpdateSuccess(persona: Persona)
    fun onPersonaUpdateError(t: Throwable)
}