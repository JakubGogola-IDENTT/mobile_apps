package com.example.zad1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NewtonAPI {
    @GET("/{operation}/{expression}")
    fun getResponse(@Path("operation") operation: String,
                    @Path("expression") expression: String) : Call<NewtonResponse>
}