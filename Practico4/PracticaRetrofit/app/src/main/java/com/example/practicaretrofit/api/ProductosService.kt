package com.example.practicaretrofit.api

import com.example.practicaretrofit.models.Producto
import com.example.practicaretrofit.models.ProductoDeleteResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductosService {
    @GET("productos/")
    fun getproductosList(): Call<List<Producto>>


    @GET("productos/{id}")
    fun getproductosById(@Path("id") id: Int): Call<Producto>

    @PUT("productos/{id}")
    fun editProducto(@Path("id") id: Int, @Body body: RequestBody): Call<Producto>

    @POST("productos")
    fun insertProducto(@Body body: RequestBody): Call<Producto>

    @DELETE("productos/{id}/")
    fun deleteProducto(@Path("id") id: Int): Call<ProductoDeleteResponse>
}