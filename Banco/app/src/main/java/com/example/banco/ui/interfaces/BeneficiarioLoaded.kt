package com.example.appmovilof.ui.interfaces

import com.example.appmovilof.models.DataBeneficiariosList

interface BeneficiarioLoaded {
    fun onBeneficiarioLoaded(dataBeneficiario: ArrayList<DataBeneficiariosList>?)
    fun onErrorLoading(error: Throwable?, message: String)
}