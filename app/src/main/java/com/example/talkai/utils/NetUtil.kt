package com.example.talkai.utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NetUtil {
    // 网络请求工具类
    private const val BASE_URL = "http://frium.top:7654/" // 替换为实际地址

    // 发送验证码
    fun sendCode(username: String, callback: NetCallback<ResultObject>) {
        val retrofit = RetrofitClient.getClient(BASE_URL)
        val service = retrofit.create(ApiService::class.java)
        service.sendVerificationCode(username).enqueue(object : Callback<ResultObject> {
            override fun onResponse(call: Call<ResultObject>, response: Response<ResultObject>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.code == 200) {
                            callback.onSuccess(it)
                        } else {
                            callback.onFailure(Exception(it.msg))
                        }
                    }
                } else {
                    callback.onFailure(Exception("请求失败"))
                }
            }

            override fun onFailure(call: Call<ResultObject>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    // 校验验证码
    fun verifyCode(request: VerifyCodeRequest, callback: NetCallback<ResultObject>) {
        val retrofit = RetrofitClient.getClient(BASE_URL)
        val service = retrofit.create(ApiService::class.java)

        service.verifyCode(request).enqueue(object : Callback<ResultObject> {
            override fun onResponse(call: Call<ResultObject>, response: Response<ResultObject>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.code == 200) {
                            callback.onSuccess(it)
                        } else {
                            callback.onFailure(Exception(it.msg))
                        }
                    }
                } else {
                    callback.onFailure(Exception("验证码校验失败"))
                }
            }

            override fun onFailure(call: Call<ResultObject>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }


    // 验证码登录
    fun loginWithCode(request: LambdaCodeLoginDTO, callback: NetCallback<ResultLoginVO>) {
        val retrofit = RetrofitClient.getClient(BASE_URL)
        val service = retrofit.create(ApiService::class.java)
        service.loginWithCode(request).enqueue(object : Callback<ResultLoginVO> {
            override fun onResponse(call: Call<ResultLoginVO>, response: Response<ResultLoginVO>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.code == 200) {
                            callback.onSuccess(it)
                        } else {
                            callback.onFailure(Exception("登录失败"))
                        }
                    }
                } else {
                    callback.onFailure(Exception("请求失败"))
                }
            }

            override fun onFailure(call: Call<ResultLoginVO>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    // 用户注册
    fun register(request: RegisterDTO, callback: NetCallback<ResultObject>) {
        val retrofit = RetrofitClient.getClient(BASE_URL)
        val service = retrofit.create(ApiService::class.java)
        service.register(request).enqueue(object : Callback<ResultObject> {
            override fun onResponse(call: Call<ResultObject>, response: Response<ResultObject>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.code == 200) {
                            callback.onSuccess(it)
                        } else {
                            callback.onFailure(Exception(it.msg))
                        }
                    }
                } else {
                    callback.onFailure(Exception("注册失败"))
                }
            }

            override fun onFailure(call: Call<ResultObject>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }


    interface NetCallback<T> {
        fun onSuccess(result: T)
        fun onFailure(t: Throwable)
    }
    data class VerifyCodeRequest(
        val phone: String,
        val code: String
    )
}