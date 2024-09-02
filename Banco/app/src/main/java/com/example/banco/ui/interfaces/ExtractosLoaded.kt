package com.example.appmovilof.ui.interfaces

import com.example.appmovilof.models.DataExtracto

interface ExtractosLoaded {
    fun onExtractosLoaded(extractosCuenta: ArrayList<DataExtracto>?)
    fun onErrorLoading(error: Throwable?, message: String)
}