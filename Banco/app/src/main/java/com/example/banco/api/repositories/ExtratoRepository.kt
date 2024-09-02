package com.example.appmovilof.api.repositories

import com.example.appmovilof.api.servicios.ExtractosInterface
import com.example.appmovilof.models.DataExtracto
import com.example.appmovilof.ui.interfaces.ExtractosLoaded
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ExtratoRepository {
    private val retrofitClient: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://apibancomoviles1.jmacboy.com")
        .build()

    fun getExtractoCuentaUser(listener: ExtractosLoaded, idCuenta: Int, access_token: String){
        val extractosCuenta = retrofitClient.create((ExtractosInterface::class.java))
        extractosCuenta.getExtracto(idCuenta, "Bearer " + access_token).enqueue(object : Callback<ArrayList<DataExtracto>>{
            override fun onResponse(
                call: Call<ArrayList<DataExtracto>>,
                response: Response<ArrayList<DataExtracto>>
            ) {
                val listaExtractosCuenta = response?.body()

                listaExtractosCuenta.let {
                    listener.onExtractosLoaded(it)
                }
            }

            override fun onFailure(call: Call<ArrayList<DataExtracto>>, t: Throwable) {
                listener.onErrorLoading(t,"Error cargando los extractos")
            }

        })
    }
}