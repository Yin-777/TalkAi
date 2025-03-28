package com.example.talkai.ui.activity.Sheets

import android.content.Context
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import com.example.talkai.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class IDLoginSheet(context: Context):BottomSheetDialog(context) {
    private lateinit var btnLogin: AppCompatButton
    private lateinit var ckBox:AppCompatCheckBox
    private lateinit var edID:AppCompatEditText
    private lateinit var edPassword:AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // 设置布局
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_login_id, null)
        setContentView(bottomSheetView)

        // 找到 BottomSheetDialog 中的按钮
        edID = bottomSheetView.findViewById<AppCompatEditText>(R.id.ed_id)
        edPassword = bottomSheetView.findViewById<AppCompatEditText>(R.id.ed_password)
        btnLogin = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_login_id)
        ckBox = bottomSheetView.findViewById<AppCompatCheckBox>(R.id.ck_box)

        setupButtons()
    }
    private fun setupButtons() {
        // 注册按钮
        btnLogin.setOnClickListener {
            handleLogin()
        }

        // checkbox
        // 监听复选框状态变化，动态更新按钮状态
        ckBox.setOnCheckedChangeListener { _, isChecked ->
            btnLogin.isEnabled = isChecked
        }
    }

    private fun handleLogin() {
        val ID = edID.toString().trim()
        val passWord = edPassword.toString().trim()

        if (ID.isEmpty()){
            showToast("ID不能为空")
            return
        }
        if(passWord.isEmpty()){
            showToast("密码不能为空")
            return
        }
        if (!ckBox.isChecked) {
            showToast("请先同意相关条款")
            return
        }

        Login()

    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun Login() {
        // 这里添加你的注册逻辑
        showToast("登录成功！")
        dismiss()
    }

}