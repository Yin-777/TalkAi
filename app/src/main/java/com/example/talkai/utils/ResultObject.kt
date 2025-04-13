package com.example.talkai.utils

// ResultObject.kt
data class ResultObject(
    val msg: String,
    val code: Int,
    val access_token: String,
    val user_info: UserInfo
)

data class UserInfo(
    val id: Int,
    val username: String,
    val nickname: String,
    val avatar: String? = null
)

