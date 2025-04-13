package com.example.talkai.network


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var token: String? = null

    fun setToken(t: String?) {
        token = t
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val original: Request = chain.request()
            val builder = original.newBuilder()
                .header("Content-Type", "application/json")
            token?.let {
                builder.header("Authorization", "Bearer $it")
            }
            val request = builder.build()
            chain.proceed(request)
        })
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://120.77.218.232:5000")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
