package com.example.talkai.ui.activity.ProgressBar


import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.example.talkai.R

class ZDYProgressDialog(context: Context) : ProgressDialog(context) {

    constructor(context: Context, theme: Int) : this(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(context)
    }

    /**
     * 初始化方法
     */
    private fun init(context: Context) {
        // 设置不可取消，点击其他区域不能取消
        setCancelable(false)
        setCanceledOnTouchOutside(false)

        setContentView(R.layout.layout_load_dialog)
        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.WRAP_CONTENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = params
    }

    override fun show() {
        super.show()
    }

    override fun dismiss() {
        super.dismiss()
    }
}