package com.example.talkai.utils


class User(zhanghao: String, mima: String) {
    // 实例属性
    var zhanghao: String = zhanghao
    var mima: String = mima

    companion object {
        // 静态属性
        var zhanghao: String = ""
            private set
        var mima: String = ""
            private set

        // 静态方法：设置静态属性
        fun setStaticZhanghao(zhanghao: String) {
            this.zhanghao = zhanghao
        }

        fun setStaticMima(mima: String) {
            this.mima = mima
        }
    }

    // 实例方法：设置静态属性
    fun setStaticZhanghaoFromInstance(zhanghao: String) {
        User.zhanghao = zhanghao
    }

    fun setStaticMimaFromInstance(mima: String) {
        User.mima = mima
    }
}