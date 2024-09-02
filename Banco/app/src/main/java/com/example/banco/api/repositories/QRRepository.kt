package com.example.appmovilof.api.repositories

import com.example.appmovilof.api.servicios.QRInterfaz
import com.example.appmovilof.models.DataCobroQR
import com.example.appmovilof.models.DataPagoQR
import com.example.appmovilof.models.DataQRPagoRespuesta
import com.example.appmovilof.models.DataRespuestaCobroQR
import com.example.appmovilof.ui.interfaces.CobroQRLoaded
import com.example.appmovilof.ui.interfaces.PagoQRLoaded
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QRRepository {
    private val retrofitClient: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://apibancomoviles1.jmacboy.com")
        .build()

    fun generarImgQR(listener: CobroQRLoaded, acces_Token: String, monto: Int?, cuentaDestino: Int, fechaLimite: String){
        val cobroQr = retrofitClient.create(QRInterfaz::class.java)
        cobroQr.generarQR("Bearer "+ acces_Token, DataCobroQR(monto, cuentaDestino, fechaLimite))
            .enqueue(object : Callback<DataRespuestaCobroQR>{
                override fun onResponse(
                    call: Call<DataRespuestaCobroQR>,
                    response: Response<DataRespuestaCobroQR>
                ) {
                    val respuestaCobroQR = response?.body()

                    respuestaCobroQR.let {
                        listener.onCobroQRLoaded(it)
                    }
                }

                override fun onFailure(call: Call<DataRespuestaCobroQR>, t: Throwable) {
                    listener.onErrorLoading(t, "Error al generar el QR")
                }

            })
    }

    fun pagarQR(listener: PagoQRLoaded,acces_Token: String, codigo: String, cuentaOrigen: Int){
        val pagoQr= retrofitClient.create(QRInterfaz::class.java)
        pagoQr.pagoQR("Bearer " + acces_Token, DataPagoQR(codigo, cuentaOrigen))
            .enqueue(object  : Callback<DataQRPagoRespuesta>{
                override fun onResponse(
                    call: Call<DataQRPagoRespuesta>,
                    response: Response<DataQRPagoRespuesta>,
                ) {
                    val pagoQRresponse = response?.body()
                    pagoQRresponse.let {
                        listener.onPagoQRLoaded(it)
                    }
                }

                override fun onFailure(call: Call<DataQRPagoRespuesta>, t: Throwable) {
                    listener.onErrorLoading(t, "Error al pagar el qr")
                }

            })
    }
}