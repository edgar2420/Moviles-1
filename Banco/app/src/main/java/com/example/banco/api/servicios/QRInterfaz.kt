package com.example.appmovilof.api.servicios

import com.example.appmovilof.models.DataCobroQR
import com.example.appmovilof.models.DataPagoQR
import com.example.appmovilof.models.DataQRPagoRespuesta
import com.example.appmovilof.models.DataRespuestaCobroQR
import retrofit2.Call
import retrofit2.http.*

interface QRInterfaz {

    @POST("/api/qr/generate")
    fun generarQR(
        @Header("AUTHORIZATION") value: String,
        @Body datosCobroQR: DataCobroQR
    ): Call<DataRespuestaCobroQR>

    fun pagoQR(
        @Header("AUTHORIZATION") value: String,
        @Body dataPagoQR: DataPagoQR
    ): Call<DataQRPagoRespuesta>


//    @FormUrlEncoded
//    @POST("/api/qr/generate")
//    fun generarQR(
//        @Header("AUTHORIZATION") value: String,
//        @Field("monto") monto: Int?,
//        @Field("cuentaDestino") cuentaDestino: Int,
//        @Field("fechaLimite") fechaLimite: String
//    ): Call<DataRespuestaCobroQR>

}