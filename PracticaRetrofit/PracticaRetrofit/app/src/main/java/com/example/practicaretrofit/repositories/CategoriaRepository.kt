package com.example.practicaretrofit.repositories

import com.example.practicaretrofit.api.CategoriaService
import com.example.practicaretrofit.models.Categoria
import com.example.practicaretrofit.models.CategoriaDeleteResponse
import com.example.practicaretrofit.models.Producto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CategoriaRepository {

    fun fetchListaCategoria(listener: CategoriaListListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val categoriaService = retrofit.create(CategoriaService::class.java)
        categoriaService.getCategoriaList().enqueue(object : Callback<List<Categoria>> {
            override fun onResponse(call: Call<List<Categoria>>, response: Response<List<Categoria>>) {
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
        CategoriaService.deleteCategoria(Categoria.id).enqueue(object : Callback<CategoriaDeleteResponse> {
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
        producto: Producto,
        listener: CategoriaInsertListener

    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body = createPostBody(nombre, producto)

        val categoriaService = retrofit.create(CategoriaService::class.java)
        categoriaService.insertCategoria(body).enqueue(object : Callback<Categoria> {
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
        producto: Producto,
        listener: CategoriaUpdateListener

    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body = createPostBody(nombre, producto)
        val CategoriasService = retrofit.create(CategoriaService::class.java)
        CategoriasService.editCategoria(id, body).enqueue(object : Callback<Categoria> {
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
        nombre: String,
        producto: Producto

    ): RequestBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("nombre", nombre)
            .addFormDataPart("producto", producto.toString())
            .build()
    }
}

interface CategoriaListListener {
    fun onCategoriaListFetched(Categoria: List<Categoria>)
    fun  onCategoriaListFetchError(t: Throwable)
}

interface CategoriaDeleteListener {
    fun  onCategoriaDeleteSuccess(response: CategoriaDeleteResponse)
    fun  onCategoriaDeleteError(t: Throwable)
}

interface CategoriaDetailListener {
    fun  onCategoriaDetailFetched(categoria: Categoria)
    fun  onCategoriaDetailFetchError(t: Throwable)
}

interface CategoriaInsertListener {
    fun  onCategoriaInsertSuccess(categoria: Categoria)
    fun  onCategoriaInsertError(t: Throwable)
}

interface CategoriaUpdateListener {
    fun  onCategoriaUpdateSuccess(categoria: Categoria)
    fun  onCategoriaUpdateError(t: Throwable)
}