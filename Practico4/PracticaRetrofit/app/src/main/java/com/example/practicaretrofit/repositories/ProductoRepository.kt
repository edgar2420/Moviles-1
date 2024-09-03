import com.example.practicaretrofit.repositories.RetrofitRepository


import com.example.practicaretrofit.api.ProductosService
import com.example.practicaretrofit.models.Categoria
import com.example.practicaretrofit.models.Producto
import com.example.practicaretrofit.models.ProductoDeleteResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ProductoRepository {

    fun fetchListaProducto(listener: ProductoListListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val productoService = retrofit.create(ProductosService::class.java)
        //Muestra el producto
        productoService.getproductosList().enqueue(object : Callback<List<Producto>> {
            override fun onResponse(call: Call<List<Producto>>, response: Response<List<Producto>>) {
                if (response.isSuccessful) {
                    val producto = response.body()
                    producto?.let {
                        listener.onProductoListFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                listener.onProductoListFetchError(t)
            }
        })

    }

    fun deleteProducto(persona: Producto, listener: ProductoDeleteListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val personaService = retrofit.create(ProductosService::class.java)
        personaService.deleteProducto(persona.id)
            .enqueue(object : Callback<ProductoDeleteResponse> {
                override fun onResponse(
                    call: Call<ProductoDeleteResponse>,
                    response: Response<ProductoDeleteResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()
                        respuesta?.let { listener.onProductoDeleteSuccess(it) }
                    }
                }

                override fun onFailure(call: Call<ProductoDeleteResponse>, t: Throwable) {
                    listener.onProductoDeleteError(t)
                }
            })
    }

    fun fetchProductoDetail(id: Int, listener: ProductoDetailListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val productoService = retrofit.create(ProductosService::class.java)
        productoService.getproductosById(id).enqueue(object : Callback<Producto> {
            override fun onResponse(call: Call<Producto>, response: Response<Producto>) {
                if (response.isSuccessful) {
                    val persona = response.body()
                    persona?.let {
                        listener.onProductoDetailFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<Producto>, t: Throwable) {
                listener.onProductoDetailFetchError(t)
            }
        })
    }

    fun saveInsertProducto(
        nombre: String,
        descripcion:String,
        precio_actual: Double,
        categoria_id: Int,
        listener: ProductoInsertListener
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body = createPostBody(nombre,descripcion, precio_actual, categoria_id)
        // Convert JSONObject to String
        val jsonObjectString = body.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val RequestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val personaService = retrofit.create(ProductosService::class.java)
        personaService.insertProducto(RequestBody).enqueue(object : Callback<Producto> {
            override fun onResponse(call: Call<Producto>, response: Response<Producto>) {
                if (response.isSuccessful) {
                    val persona = response.body()
                    persona?.let {
                        listener.onProductoInsertSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Producto>, t: Throwable) {
                listener.onProductoInsertError(t)
            }
        })
    }


    fun saveUpdateProducto(
        id: Int,
        nombre: String,
        descripcion: String,
        precio_actual: Double,
        categoria_id: Int,
        listener: ProductoUpdateListener
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body = createPostBody(nombre, descripcion , precio_actual, categoria_id)
        // Convert JSONObject to String
        val jsonObjectString = body.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val RequestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val productosService = retrofit.create(ProductosService::class.java)

        productosService.editProducto(id, RequestBody).enqueue(object : Callback<Producto> {
            override fun onResponse(call: Call<Producto>, response: Response<Producto>) {
                if (response.isSuccessful) {
                    val persona = response.body()
                    persona?.let {
                        listener.onProductoUpdateSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Producto>, t: Throwable) {
                listener.onProductoUpdateError(t)
            }
        })
    }

    private fun createPostBody(
        nombre: String,
        descripcion: String,
        precio_actual: Double,
        idCategoria: Int,


        ): JSONObject {
        val json = JSONObject()
        json.put("nombre", nombre)
        json.put("descripcion", descripcion)
        json.put("precio_actual", precio_actual)
        json.put("categoria_id", idCategoria)
        return json
    }
}

interface ProductoListListener {
    fun onProductoListFetched(producto: List<Producto>)
    fun  onProductoListFetchError(t: Throwable)
}

interface ProductoDeleteListener {
    fun  onProductoDeleteSuccess(response: ProductoDeleteResponse)
    fun  onProductoDeleteError(t: Throwable)
}

interface ProductoDetailListener {
    fun  onProductoDetailFetched(producto: Producto)
    fun  onProductoDetailFetchError(t: Throwable)
}

interface ProductoInsertListener {
    fun  onProductoInsertSuccess(producto: Producto)
    fun  onProductoInsertError(t: Throwable)
}

interface ProductoUpdateListener {
    fun  onProductoUpdateSuccess(producto: Producto)
    fun  onProductoUpdateError(t: Throwable)
}