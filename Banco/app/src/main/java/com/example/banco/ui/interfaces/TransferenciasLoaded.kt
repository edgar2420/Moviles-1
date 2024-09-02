package com.example.appmovilof.ui.interfaces

import com.example.appmovilof.models.DataBeneficiario
import com.example.appmovilof.models.DataRespuestaTransferencia


interface TransferenciasLoaded {
    fun onTransferenciaLoading(respuestaTransferencia: DataRespuestaTransferencia?)
    fun onErrorLoading(error: Throwable?, message: String)
}