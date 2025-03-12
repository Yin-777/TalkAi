package com.example.talkai.ui.activity.Sheets


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.example.talkai.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class RegisterSheet(context: Context):BottomSheetDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 设置布局
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_register,null)
        setContentView(bottomSheetView)

        // 找到BottomSheetDialog中的按钮
        val btnsent = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_sent)
        val btnRegister = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_register)

        // 设置按钮点击事件
        btnsent.setOnClickListener{
            dismiss()// 关闭 BottomSheetDialog
        }

        btnRegister.setOnClickListener{
            // 关闭 BottomSheetDialog
        }
    }
}