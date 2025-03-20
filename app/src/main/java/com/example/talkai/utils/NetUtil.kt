package com.example.talkai.utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NetUtil {
    // 网络请求工具类
    private const val BASE_URL = "https://api.github.com/"

    fun getUser(username: String, callback: NetCallback<User>) {
        // 获取 Retrofit 实例
        val retrofit = RetrofitClient.getClient(BASE_URL)
        // 创建 API 接口实例
        val apiService = retrofit.create(ApiService::class.java)
        // 发起网络请求
        val call = apiService.getUser(username)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onFailure(Exception("Response is not successful"))
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    interface NetCallback<T> {
        fun onSuccess(result: T)
        fun onFailure(t: Throwable)
    }
}