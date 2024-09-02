package com.example.appmovilof.ui.interfaces

import com.example.appmovilof.models.DataBeneficiariosList

interface BeneficiarioCreatedLoaded {
    fun onBeneficiarioCreatedLoaded(dataBeneficiario: DataBeneficiariosList?)
    fun onErrorLoading(error: Throwable?, message: String)
}