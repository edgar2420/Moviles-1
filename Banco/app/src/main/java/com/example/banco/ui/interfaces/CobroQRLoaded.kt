package com.example.appmovilof.ui.interfaces

import com.example.appmovilof.models.DataRespuestaCobroQR

interface CobroQRLoaded {
    fun onCobroQRLoaded(dataRespuestaQR: DataRespuestaCobroQR?)
    fun onErrorLoading(error: Throwable?, message: String)
}