package com.example.talkai.utils

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// ApiService.kt
interface ApiService {
    // 发送验证码
    @GET("user/code")
    fun sendVerificationCode(
        @Query("username") username: String
    ): Call<ResultObject>

    // 验证码登录
    @POST("user/login/code")
    fun loginWithCode(
        @Body request: LambdaCodeLoginDTO
    ): Call<ResultLoginVO>

    // 注册接口
    @POST("user/register")
    fun register(
        @Body request: RegisterDTO
    ): Call<ResultObject>

    // 验证码校验
    @POST("/user/verifyCode")
    fun verifyCode(@Body request: NetUtil.VerifyCodeRequest): Call<ResultObject>
}

// 新增DTO类
data class LambdaCodeLoginDTO(
    val username: String,
    val code: String
)

// 新增注册DTO
data class RegisterDTO(
    val username: String,
    val code: String,
    val password: String
)

// 响应数据类
data class ResultObject(
    val code: Int,
    val msg: String,
    val data: Any?
)

data class ResultLoginVO(
    val code: Int,
    val data: LoginResult?
)

data class LoginResult(
    val token: String,
    val userInfo: User
)
