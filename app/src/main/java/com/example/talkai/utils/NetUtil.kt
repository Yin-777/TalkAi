package com.example.talkai.utils

import com.example.talkai.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NetUtil {

    interface NetCallback<T> {
        fun onSuccess(result: T)
        fun onFailure(t: Throwable)
    }

    fun register(data: RegisterDTO, callback: NetCallback<ResultObject>) {
        val call = RetrofitClient.apiService.register(data)
        call.enqueue(object : Callback<ResultObject> {
            override fun onResponse(call: Call<ResultObject>, response: Response<ResultObject>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<ResultObject>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    fun login(username: String, password: String, callback: NetCallback<ResultObject>) {
        val data = mapOf("username" to username, "password" to password)
        val call = RetrofitClient.apiService.login(data)
        call.enqueue(object : Callback<ResultObject> {
            override fun onResponse(call: Call<ResultObject>, response: Response<ResultObject>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<ResultObject>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

}
