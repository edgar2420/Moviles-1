package com.example.appmovilof.ui.interfaces

import com.example.appmovilof.models.DataRespuestaRegistro

interface RegistroLoaded {
    fun onRegistroUsuarioLoaded(dataRespuestaRegistro: DataRespuestaRegistro?)
    fun onErrorLoading(error: Throwable?, message: String)
}