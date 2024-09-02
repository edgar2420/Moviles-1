package com.example.appmovilof.ui.interfaces

import com.example.appmovilof.models.DataMovimiento

interface RetiroIngresoLoaded {
    fun onRetiroIngresoLoaded(dataMovimiento: DataMovimiento?)
    fun onErrorLoading(error: Throwable?, message: String)
}