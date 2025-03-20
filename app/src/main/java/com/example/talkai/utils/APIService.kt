package com.example.talkai.utils

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<User>
}