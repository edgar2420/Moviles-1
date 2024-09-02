package com.example.appmovilof.ui.interfaces

import com.example.appmovilof.models.DataQRPagoRespuesta

interface PagoQRLoaded {
    fun onPagoQRLoaded(dataQRPagoRespuesta: DataQRPagoRespuesta?)
    fun onErrorLoading(error: Throwable?, message: String)
}