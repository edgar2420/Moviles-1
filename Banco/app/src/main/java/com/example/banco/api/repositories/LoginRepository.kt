package com.example.appmovilof.api.repositories

import com.example.appmovilof.api.servicios.LoginInterface
import com.example.appmovilof.models.DataLogin
import com.example.appmovilof.models.Token
import com.example.appmovilof.ui.interfaces.LoginLoaded
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginRepository {
    private val retrofitClient: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://apibancomoviles1.jmacboy.com")
        .build()

    fun loginUser(listener: LoginLoaded, email: String, password: String) {
        val loginUser = retrofitClient.create(LoginInterface::class.java)
        loginUser.login(DataLogin(email, password))
            .enqueue(object : Callback<Token> {
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    val login_token = response?.body()

                    login_token.let {
                            listener.onLoginLoaded(it)
                    }
                }
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    listener.onErrorLoading(t, "Datos incorrectos")
                }

            })
    }
}