package com.example.talkai.utils
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, DBNAME, null, VERSION) {

    companion object {
        private const val DBNAME = "输入你想创建的数据库名" // 数据库名
        private const val VERSION = 1 // 数据库版本
    }

    // 1. 创建数据库
    override fun onCreate(db: SQLiteDatabase) {
        // 创建用户表，储存账号和密码
        db.execSQL("create table yonghu(id integer primary key autoincrement, zhanghao varchar(10), mima varchar(15))")
    }

    // 2. 升级数据库
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // 这里可以添加数据库升级的逻辑
    }
}