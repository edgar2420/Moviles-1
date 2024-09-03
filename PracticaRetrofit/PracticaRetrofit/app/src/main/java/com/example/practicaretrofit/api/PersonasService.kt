package com.example.practicaretrofit.api

import com.example.practicaretrofit.models.Persona
import com.example.practicaretrofit.models.PersonaDeleteResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PersonasService {
    @GET("personas")
    fun getPersonasList(): Call<List<Persona>>


    @GET("personas/{id}")
    fun getPersonasById(@Path("id") id: Int): Call<Persona>

    @POST("personas/{id}")
    fun editPersona(@Path("id") id: Int, @Body body: RequestBody): Call<Persona>

    @POST("personas")
    fun insertPersona(@Body body: RequestBody): Call<Persona>

    @POST("personas/{id}/delete")

    fun deletePersona(@Path("id") id: Int): Call<PersonaDeleteResponse>
}