package com.example.appmovilof.models

import retrofit2.http.Field

class DataTransferencia(
    var beneficiario: Int,
    var cuentaOrigen: Int,
    var monto: Int?,
    var descripcion: String
) {
}