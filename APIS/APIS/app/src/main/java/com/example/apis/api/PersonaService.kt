package com.example.apis.api

import com.example.apis.models.Persona
import com.example.apis.models.PersonaDeleteResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PersonaService {
    @GET("personas")
    fun getPersonas() : Call<List<Persona>>

    @GET("personas/{id}")
    fun getPersonaById(@Path("id")id:Long) : Call<Persona>

    @POST("personas/{id}")
    fun updatePersonaById(@Path("id")id:Long, @Body body : RequestBody) : Call<Persona>

    @POST("personas")
    fun createPersona(@Body body : RequestBody) : Call<Persona>

    @POST("personas/{id}/delete")
    fun deletePersonaById(@Path("id")id:Long) : Call<PersonaDeleteResponse>
}