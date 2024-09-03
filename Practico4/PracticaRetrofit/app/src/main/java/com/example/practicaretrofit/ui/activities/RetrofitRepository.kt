package com.example.practicaretrofit.ui.activities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepository {
    companion object {
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://practico4moviles.jmacboy.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}