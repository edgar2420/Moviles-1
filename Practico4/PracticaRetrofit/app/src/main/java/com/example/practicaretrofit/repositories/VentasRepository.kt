package com.example.practicaretrofit.repositories

import com.example.practicaretrofit.api.VentasService
import com.example.practicaretrofit.models.Ventas
import com.example.practicaretrofit.models.VentasDeleteResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object VentasRepository {

    fun fetchListaVentas(listener: VentasListListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val ventasService = retrofit.create(VentasService::class.java)
        ventasService.getVentasList().enqueue(object : Callback<List<Ventas>> {
            override fun onResponse(call: Call<List<Ventas>>, response: Response<List<Ventas>>) {
                if (response.isSuccessful) {
                    val Categoria = response.body()
                    Categoria?.let {
                        listener.onVentasListFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Ventas>>, t: Throwable) {
                listener.onVentasListFetchError(t)
            }
        })

    }

    fun deleteVentas(ventas: Ventas, listener: VentasDeleteListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val ventasService = retrofit.create(VentasService::class.java)
        ventasService.deleteVentas(ventas.id).enqueue(object : Callback<VentasDeleteResponse> {
            override fun onResponse(
                call: Call<VentasDeleteResponse>,
                response: Response<VentasDeleteResponse>
            ) {
                if (response.isSuccessful) {
                    val respuesta = response.body()
                    respuesta?.let { listener.onVentasDeleteSuccess(it)  }
                }
            }

            override fun onFailure(call: Call<VentasDeleteResponse>, t: Throwable) {
                listener.onVentasDeleteError(t)
            }
        })
    }

    fun fetchVentasDetail(id: Int, listener: VentasDetailListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val ventasService = retrofit.create(VentasService::class.java)
        ventasService.getVentasById(id).enqueue(object : Callback<Ventas> {
            override fun onResponse(call: Call<Ventas>, response: Response<Ventas>) {
                if (response.isSuccessful) {
                    val categoria = response.body()
                    categoria?.let {
                        listener.onVentasDetailFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<Ventas>, t: Throwable) {
                listener.onVentasDetailFetchError(t)
            }
        })
    }

    fun saveInsertVentas(

        nombre: String,
        nit: String,
        usuario:String,
        listener: VentasInsertListener

    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body = createPostBody(nombre,nit,usuario)

        val ventasService = retrofit.create(VentasService::class.java)
        ventasService.insertVentas(body).enqueue(object : Callback<Ventas> {
            override fun onResponse(call: Call<Ventas>, response: Response<Ventas>) {
                if (response.isSuccessful) {
                    val categoria = response.body()
                    categoria?.let {
                        listener.onVentasInsertSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Ventas>, t: Throwable) {
                listener.onVentasInsertError(t)
            }
        })
    }


    fun saveUpdateVentas(
        id: Int,
        nombre: String,
        nit:String,
        usuario: String,
        listener: VentasUpdateListener

    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body = createPostBody(nombre, nit,usuario)
        val ventasService = retrofit.create(VentasService::class.java)
        ventasService.editVentas(id, body).enqueue(object : Callback<Ventas> {
            override fun onResponse(call: Call<Ventas>, response: Response<Ventas>) {
                if (response.isSuccessful) {
                    val categoria = response.body()
                    categoria?.let {
                        listener.onVentasUpdateSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Ventas>, t: Throwable) {
                listener.onVentasUpdateError(t)
            }
        })
    }

    private fun createPostBody(
        nombre: String,
        nit:String,
        usuario: String,


        ): RequestBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("nombre", nombre)
            .addFormDataPart("nit", nit)
            .addFormDataPart("usuario", usuario)

            .build()
    }
}

interface VentasListListener {
    fun onVentasListFetched(Categoria: List<Ventas>)
    fun  onVentasListFetchError(t: Throwable)
}

interface VentasDeleteListener {
    fun  onVentasDeleteSuccess(response: VentasDeleteResponse)
    fun  onVentasDeleteError(t: Throwable)
}

interface VentasDetailListener {
    fun  onVentasDetailFetched(ventas: Ventas)
    fun  onVentasDetailFetchError(t: Throwable)
}

interface VentasInsertListener {
    fun  onVentasInsertSuccess(ventas: Ventas)
    fun  onVentasInsertError(t: Throwable)
}

interface VentasUpdateListener {
    fun  onVentasUpdateSuccess(ventas: Ventas)
    fun  onVentasUpdateError(t: Throwable)
}