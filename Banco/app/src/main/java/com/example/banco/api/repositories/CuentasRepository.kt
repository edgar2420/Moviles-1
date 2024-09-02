package com.example.appmovilof.api.repositories

import com.example.appmovilof.api.servicios.CuentasInterface
import com.example.appmovilof.models.DataCuentaUser
import com.example.appmovilof.models.DataIngresoEgreso
import com.example.appmovilof.models.DataMovimiento
import com.example.appmovilof.ui.interfaces.CuentasLoaded
import com.example.appmovilof.ui.interfaces.RetiroIngresoLoaded
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CuentasRepository {
    private val retrofitClient: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://apibancomoviles1.jmacboy.com")
        .build()

    fun getCuentasUser(listener: CuentasLoaded, access_token: String) {
        val cuentaUser = retrofitClient.create(CuentasInterface::class.java)
        cuentaUser.getCuentas("Bearer " + access_token)
            .enqueue(object : Callback<ArrayList<DataCuentaUser>> {
                override fun onResponse(
                    call: Call<ArrayList<DataCuentaUser>>,
                    response: Response<ArrayList<DataCuentaUser>>
                ) {
                    val listaCuentasUser = response?.body()

                    listaCuentasUser.let {
                        listener.onLoginLoaded(it)
                    }
                }

                override fun onFailure(call: Call<ArrayList<DataCuentaUser>>, t: Throwable) {
                    listener.onErrorLoading(t, "Error cargando la lista de cuentas")
                }

            })
    }

    fun addCuentaUser(listener: CuentasLoaded, access_token: String) {
        val cuentaUser = retrofitClient.create(CuentasInterface::class.java)
        cuentaUser.addCuenta("Bearer " + access_token).enqueue(object : Callback<DataCuentaUser> {
            override fun onResponse(
                call: Call<DataCuentaUser>,
                response: Response<DataCuentaUser>
            ) {
                val response = response.body()
            }

            override fun onFailure(call: Call<DataCuentaUser>, t: Throwable) {
                listener.onErrorLoading(t, "Error cargando la lista de cuentas")
            }

        })
    }

    fun retiroDineroCuenta(
        listener: RetiroIngresoLoaded,
        access_token: String, id: Int, monto: Int?, detalle: String ){
        val retiroCuentaUser = retrofitClient.create((CuentasInterface::class.java))
        retiroCuentaUser.retirar(id, "Bearer " + access_token, DataIngresoEgreso(detalle, monto))
            .enqueue(object : Callback<DataMovimiento>{
                override fun onResponse(
                    call: Call<DataMovimiento>,
                    response: Response<DataMovimiento>
                ) {

                    val dataMovimiento = response?.body()

                    dataMovimiento.let {
                        listener.onRetiroIngresoLoaded(it)
                    }

                }

                override fun onFailure(call: Call<DataMovimiento>, t: Throwable) {
                    listener.onErrorLoading(t, "Error al realizar el retiro")
                }

            })
    }

    fun ingresoDineroCuenta(
        listener: RetiroIngresoLoaded,
        access_token: String, id: Int, monto: Int?, detalle: String ){
        val retiroCuentaUser = retrofitClient.create((CuentasInterface::class.java))
        retiroCuentaUser.ingresar(id, "Bearer " + access_token, DataIngresoEgreso(detalle, monto))
            .enqueue(object : Callback<DataMovimiento>{
                override fun onResponse(
                    call: Call<DataMovimiento>,
                    response: Response<DataMovimiento>
                ) {

                    val dataMovimiento = response?.body()

                    dataMovimiento.let {
                        listener.onRetiroIngresoLoaded(it)
                    }

                }

                override fun onFailure(call: Call<DataMovimiento>, t: Throwable) {
                    listener.onErrorLoading(t, "Error al realizar el retiro")
                }

            })
    }


    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://apibancomoviles1.jmacboy.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}