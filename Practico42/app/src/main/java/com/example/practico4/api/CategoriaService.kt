package com.example.practicaretrofit.api

import com.example.practicaretrofit.models.Categoria
import com.example.practicaretrofit.models.CategoriaDeleteResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CategoriaService {

    @GET("categorias")
    fun getCategoriaList(): Call<List<Categoria>>

    @GET("categorias/{id}")
    fun getCategoriaById(@Path("id") id: Int): Call<Categoria>

    @PUT("categorias/{id}")
    fun editCategoria(@Path("id") id: Int, @Body body: RequestBody): Call<Categoria>

    @POST("categorias")
    fun insertCategoria(@Body body: RequestBody): Call<Categoria>

    @DELETE("categorias/{id}/delete")

    fun deleteCategoria(@Path("id") id: Int): Call<CategoriaDeleteResponse>
}