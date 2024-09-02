package com.example.appmovilof.ui.interfaces

import com.example.appmovilof.models.DataCuentaUser

interface CuentasLoaded {
    fun onLoginLoaded(cuentaUser: ArrayList<DataCuentaUser>?)
    fun onErrorLoading(error: Throwable?, message: String)
}