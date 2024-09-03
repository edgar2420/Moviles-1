package com.example.practicaretrofit.repositories

import com.example.practicaretrofit.api.CategoriaService
import com.example.practicaretrofit.models.Categoria
import com.example.practicaretrofit.models.CategoriaDeleteResponse
import com.example.practicaretrofit.models.Producto
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CategoriaRepository {

    fun fetchListaCategoria(listener: CategoriaListListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        //definimos el servicio
        val categoriaService = retrofit.create(CategoriaService::class.java)
        //pide la lista de categorias al servicio
        categoriaService.getCategoriaList().enqueue(object : Callback<List<Categoria>> {
            override fun onResponse(
                call: Call<List<Categoria>>,
                response: Response<List<Categoria>>
            ) {
                if (response.isSuccessful) {
                    val Categoria = response.body()
                    Categoria?.let {
                        listener.onCategoriaListFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Categoria>>, t: Throwable) {
                listener.onCategoriaListFetchError(t)
            }
        })

    }

    fun deleteCategoria(Categoria: Categoria, listener: CategoriaDeleteListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val CategoriaService = retrofit.create(CategoriaService::class.java)
        CategoriaService.deleteCategoria(Categoria.id)
            .enqueue(object : Callback<CategoriaDeleteResponse> {
                override fun onResponse(
                    call: Call<CategoriaDeleteResponse>,
                    response: Response<CategoriaDeleteResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()
                        respuesta?.let { listener.onCategoriaDeleteSuccess(it) }
                    }
                }

                override fun onFailure(call: Call<CategoriaDeleteResponse>, t: Throwable) {
                    listener.onCategoriaDeleteError(t)
                }
            })
    }
    //Muestra una categoria
    fun fetchcategoriaDetail(id: Int, listener: CategoriaDetailListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val CategoriaService = retrofit.create(CategoriaService::class.java)
        CategoriaService.getCategoriaById(id).enqueue(object : Callback<Categoria> {
            override fun onResponse(call: Call<Categoria>, response: Response<Categoria>) {
                if (response.isSuccessful) {
                    val categoria = response.body()
                    categoria?.let {
                        listener.onCategoriaDetailFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<Categoria>, t: Throwable) {
                listener.onCategoriaDetailFetchError(t)
            }
        })
    }

    fun saveInsertCategoria(

        nombre: String,
        listener: CategoriaInsertListener

    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body = createPostBody(nombre)
        // Convert JSONObject to String
        val jsonObjectString = body.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val RequestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val categoriaService = retrofit.create(CategoriaService::class.java)
        categoriaService.insertCategoria(RequestBody).enqueue(object : Callback<Categoria> {
            override fun onResponse(call: Call<Categoria>, response: Response<Categoria>) {
                if (response.isSuccessful) {
                    val categoria = response.body()
                    categoria?.let {
                        listener.onCategoriaInsertSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Categoria>, t: Throwable) {
                listener.onCategoriaInsertError(t)
            }
        })
    }


    fun saveUpdateCategoria(
        id: Int,
        nombre: String,
        listener: CategoriaUpdateListener

    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body = createPostBody(nombre)

        // Convert JSONObject to String
        val jsonObjectString = body.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val RequestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val CategoriasService = retrofit.create(CategoriaService::class.java)
        CategoriasService.editCategoria(id, RequestBody).enqueue(object : Callback<Categoria> {
            override fun onResponse(call: Call<Categoria>, response: Response<Categoria>) {
                if (response.isSuccessful) {
                    val categoria = response.body()
                    categoria?.let {
                        listener.onCategoriaUpdateSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Categoria>, t: Throwable) {
                listener.onCategoriaUpdateError(t)
            }
        })
    }

    private fun createPostBody(
        nombre: String

    ): JSONObject {
        val json = JSONObject()
        json.put("nombre", nombre)
        return json
    }


    interface CategoriaListListener {
        fun onCategoriaListFetched(Categoria: List<Categoria>)
        fun onCategoriaListFetchError(t: Throwable)
    }

    interface CategoriaDeleteListener {
        fun onCategoriaDeleteSuccess(response: CategoriaDeleteResponse)
        fun onCategoriaDeleteError(t: Throwable)
    }

    interface CategoriaDetailListener {
        fun onCategoriaDetailFetched(categoria: Categoria)
        fun onCategoriaDetailFetchError(t: Throwable)
    }

    interface CategoriaInsertListener {
        fun onCategoriaInsertSuccess(categoria: Categoria)
        fun onCategoriaInsertError(t: Throwable)
    }

    interface CategoriaUpdateListener {
        fun onCategoriaUpdateSuccess(categoria: Categoria)
        fun onCategoriaUpdateError(t: Throwable)
    }
}