package com.example.banco.api.servicios

import com.example.appmovilof.models.DataBeneficiario
import com.example.appmovilof.models.DataBeneficiariosList
import com.example.appmovilof.models.DataRespuestaTransferencia
import com.example.appmovilof.models.DataTransferencia
import com.example.banco.models.*
import retrofit2.Call
import retrofit2.http.*

interface BeneficiarioInterfaz {

    @POST("/api/recipients")
    fun registrarBeneficiario(
        @Header("AUTHORIZATION") value: String,
        @Body dataBeneficiario: DataBeneficiario
    ): Call<DataBeneficiariosList>

    @GET("/api/recipients")
    fun getBeneficiarios(
        @Header("AUTHORIZATION") value: String
    ): Call<ArrayList<DataBeneficiariosList>>

    @POST("/api/accounts/transfer")
    fun realizarTransferencia(
        @Header("AUTHORIZATION") value: String,
        @Body dataTransferencia: DataTransferencia
    ): Call<DataRespuestaTransferencia>
}