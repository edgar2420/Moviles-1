package com.example.appmovilof.api.repositories

import com.example.appmovilof.api.servicios.RegistroInterfaz
import com.example.appmovilof.models.DataRegistroUser
import com.example.appmovilof.models.DataRespuestaRegistro
import com.example.appmovilof.ui.interfaces.RegistroLoaded
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.RequestBody

object RegistroRepository {
    private val retrofitClient: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://apibancomoviles1.jmacboy.com")
        .build()

    fun registrarUsuario(
        listener: RegistroLoaded,
        nombreCompleto: String,
        email: String,
        password: String,
        ci: String,
        fechaNacimiento: String,
    ) {
        val registroUser = retrofitClient.create(RegistroInterfaz::class.java)


        registroUser.registrar(DataRegistroUser(nombreCompleto, email, password, ci, fechaNacimiento))
            .enqueue(object : Callback<DataRespuestaRegistro> {
                override fun onResponse(
                    call: Call<DataRespuestaRegistro>, response: Response<DataRespuestaRegistro>,
                ) {

                    //println("Dentro")
                    var respuestaRegistro = response?.body()
                    respuestaRegistro.let {
                        listener.onRegistroUsuarioLoaded(it)
                    }
                }

                override fun onFailure(call: Call<DataRespuestaRegistro>, t: Throwable) {
                    listener.onErrorLoading(t, "Error resgistrando el usuario")
                }

            })
    }
}