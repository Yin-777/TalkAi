package com.example.talkai.utils

import android.util.Log

object LogUtils {
    // 控制日志开关，控制不同级别的日志
    private var logLevel: Int = 0  // 0 - VERBOSE, 1 - DEBUG, 2 - INFO, 3 - WARN, 4 - ERROR

    // 日志级别常量
    private const val VERBOSE = 0
    private const val DEBUG = 1
    private const val INFO = 2
    private const val WARN = 3
    private const val ERROR = 4

    // 打印调试日志
    fun d(clazz: Class<*>, log: String) {
        if (logLevel <= DEBUG) {  // 判断当前日志等级是否允许输出调试日志
            Log.d(clazz.simpleName, log)
        }
    }

    // 打印信息日志
    fun i(clazz: Class<*>, log: String) {
        if (logLevel <= INFO) {  // 判断当前日志等级是否允许输出信息日志
            Log.i(clazz.simpleName, log)
        }
    }

    // 打印警告日志
    fun w(clazz: Class<*>, log: String) {
        if (logLevel <= WARN) {  // 判断当前日志等级是否允许输出警告日志
            Log.w(clazz.simpleName, log)
        }
    }

    // 打印错误日志
    fun e(clazz: Class<*>, log: String) {
        if (logLevel <= ERROR) {  // 判断当前日志等级是否允许输出错误日志
            Log.e(clazz.simpleName, log)
        }
    }

    // 设置日志等级
    fun setLogLevel(level: Int) {
        logLevel = level
    }

    // 获取当前日志等级
    fun getLogLevel(): Int {
        return logLevel
    }
}