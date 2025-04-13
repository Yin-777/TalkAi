package com.example.talkai.network

import com.example.talkai.utils.RegisterDTO
import com.example.talkai.utils.ResultObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    // 用户注册
    @POST("/auth/register")
    fun register(@Body body: RegisterDTO): Call<ResultObject>


    // 用户登录
    @POST("/auth/login")
    fun login(@Body data: Map<String, String>): Call<ResultObject>



}