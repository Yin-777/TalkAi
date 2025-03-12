package com.example.talkai.ui.activity.Sheets

import android.content.Context
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.talkai.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class PhoneLoginSheet(context: Context):BottomSheetDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 设置布局
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_login_phone,null)

        setContentView(bottomSheetView)

        // 找到BottomSheetDialog中的按钮
        val btnLogin = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_login_phone)

        // 设置按钮点击事件
        btnLogin.setOnClickListener{
            dismiss()// 关闭 BottomSheetDialog
        }
    }
}