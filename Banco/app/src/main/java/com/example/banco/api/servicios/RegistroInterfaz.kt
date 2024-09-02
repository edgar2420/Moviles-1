package com.example.appmovilof.api.servicios

import com.example.appmovilof.models.DataRegistroUser
import com.example.appmovilof.models.DataRespuestaRegistro
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistroInterfaz {

    //@Headers("Content-Type: application/json")
    @POST("/api/register")
    fun registrar(@Body dataRegistroUser: DataRegistroUser): Call<DataRespuestaRegistro>

}