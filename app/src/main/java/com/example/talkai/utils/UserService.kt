package com.example.talkai.utils

import android.content.Context

class UserService(context: Context) {
    private val dbHelper by lazy { MyDBHelper(context) } // 懒加载初始化

    fun login(zhanghao: String, mima: String): Boolean =
        dbHelper.readableDatabase.rawQuery(
            "SELECT * FROM yonghu WHERE zhanghao=? AND mima=?",
            arrayOf(zhanghao, mima)
        ).use { it.moveToFirst() }
}