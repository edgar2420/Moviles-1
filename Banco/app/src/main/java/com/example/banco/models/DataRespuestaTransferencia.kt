package com.example.appmovilof.models

class DataRespuestaTransferencia(
    var ingreso: DataMovimientoRespuesta,
    var egreso: DataMovimientoRespuesta
) {
    override fun toString(): String {
        return "(ingreso=$ingreso, \negreso=$egreso)"
    }
}
