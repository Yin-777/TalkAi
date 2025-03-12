package com.example.talkai.utils

import android.widget.Toast
import com.example.talkai.base.BaseApplication

object ToastUtil {
    private var toast: Toast? = null
    fun showToast(tips: String) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getContext(), tips, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(tips)
        }
        toast?.show()
    }
}