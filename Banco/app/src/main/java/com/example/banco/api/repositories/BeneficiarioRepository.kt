package com.example.banco.api.repositories


import com.example.appmovilof.models.DataBeneficiario
import com.example.appmovilof.models.DataBeneficiariosList
import com.example.appmovilof.models.DataRespuestaTransferencia
import com.example.appmovilof.models.DataTransferencia
import com.example.appmovilof.ui.interfaces.BeneficiarioCreatedLoaded
import com.example.appmovilof.ui.interfaces.BeneficiarioLoaded
import com.example.appmovilof.ui.interfaces.TransferenciasLoaded
import com.example.banco.api.servicios.BeneficiarioInterfaz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BeneficiarioRepository {
    private val retrofitClient: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://apibancomoviles1.jmacboy.com")
        .build()

    fun insertarBeneficiario(
        listener: BeneficiarioCreatedLoaded,
        acces_token: String,
        nroCuenta: String,
        ci: String,
        nombreCompleto: String
    ) {
        val registroBeneficiario = retrofitClient.create(BeneficiarioInterfaz::class.java)
        registroBeneficiario.registrarBeneficiario(
            "Bearer " + acces_token,
            DataBeneficiario(nroCuenta, ci, nombreCompleto)
        ).enqueue(object : Callback<DataBeneficiariosList> {
            override fun onResponse(
                call: Call<DataBeneficiariosList>,
                response: Response<DataBeneficiariosList>
            ) {
                val response = response.body()
                response.let {
                    listener.onBeneficiarioCreatedLoaded(it)
                }
            }

            override fun onFailure(call: Call<DataBeneficiariosList>, t: Throwable) {
                listener.onErrorLoading(t, "Error cargando al registrar beneficiario")
            }

        })

    }

    fun getListaBeneficiarios(listener: BeneficiarioLoaded, acces_token: String) {
        val getBeneficiarios = retrofitClient.create(BeneficiarioInterfaz::class.java)
        getBeneficiarios.getBeneficiarios("Bearer " + acces_token)
            .enqueue(object : Callback<ArrayList<DataBeneficiariosList>> {
                override fun onResponse(
                    call: retrofit2.Call<ArrayList<DataBeneficiariosList>>,
                    response: Response<ArrayList<DataBeneficiariosList>>
                ) {
                    val listaBeneficiarios = response?.body()
                    listaBeneficiarios.let {
                        listener.onBeneficiarioLoaded(it)
                    }
                }

                override fun onFailure(
                    call: Call<ArrayList<DataBeneficiariosList>>,
                    t: Throwable
                ) {
                    listener.onErrorLoading(t, "Error cargando la lista de beneficiarios")
                }

            })
    }

    fun realizarTransferenciaBeneficiario(
        listener: TransferenciasLoaded,
        acces_token: String,
        idBeneficiario: Int,
        idCuenta: Int,
        monto: Int?,
        descripcion: String
    ) {
        val transferirDineroBeneficiario = retrofitClient.create((BeneficiarioInterfaz::class.java))
        transferirDineroBeneficiario.realizarTransferencia("Bearer "+ acces_token, DataTransferencia(idBeneficiario, idCuenta,monto,descripcion))
            .enqueue(object : Callback<DataRespuestaTransferencia>{
                override fun onResponse(
                    call: Call<DataRespuestaTransferencia>,
                    response: Response<DataRespuestaTransferencia>
                ) {
                    val datosMovimiento = response?.body()
                    datosMovimiento.let {
                        listener.onTransferenciaLoading(it)
                    }
                }

                override fun onFailure(call: Call<DataRespuestaTransferencia>, t: Throwable) {
                    listener.onErrorLoading(t, "Error al realizar la transferencia")
                }

            }
        )

    }

}