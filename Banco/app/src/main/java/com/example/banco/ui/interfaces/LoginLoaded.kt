package com.example.appmovilof.ui.interfaces

import com.example.appmovilof.models.Token

interface LoginLoaded {
    fun onLoginLoaded(token: Token?)
    fun onErrorLoading(error: Throwable?, message: String)
}