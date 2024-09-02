package com.example.practicaretrofit.api

import com.example.practicaretrofit.models.Ventas
import com.example.practicaretrofit.models.VentasDeleteResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VentasService {

    @GET("ventas")
    fun getVentasList(): Call<List<Ventas>>

    @GET("ventas/{id}")
    fun getVentasById(@Path("id") id: Int): Call<Ventas>

    @POST("ventas/{id}")
    fun editVentas(@Path("id") id: Int, @Body body: RequestBody): Call<Ventas>

    @POST("ventas")
    fun insertVentas(@Body body: RequestBody): Call<Ventas>

    @POST("ventas/{id}/delete")

    fun deleteVentas(@Path("id") id: Int): Call<VentasDeleteResponse>
}