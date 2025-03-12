package com.example.talkai.ui.activity.Sheets

import android.content.Context
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.talkai.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class IDLoginSheet(context: Context):BottomSheetDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 设置布局
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_login_id, null)
        setContentView(bottomSheetView)

        // 找到 BottomSheetDialog 中的按钮
//        val edID = bottomSheetView.findViewById<AppCompatEditText>(R.id.ed_id)
//        val edPassword = bottomSheetView.findViewById<AppCompatEditText>(R.id.ed_password)
        val btnLogin = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_login_id)

        // 设置按钮点击事件
        btnLogin.setOnClickListener {
//            handleEmailLogin()
            dismiss() // 关闭 BottomSheetDialog
        }
    }


}