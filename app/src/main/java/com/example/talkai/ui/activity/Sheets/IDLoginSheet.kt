package com.example.talkai.ui.activity.Sheets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import com.example.talkai.R
import com.example.talkai.ui.activity.ShowActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class IDLoginSheet(context: Context):BottomSheetDialog(context) {
    private lateinit var btnLogin: AppCompatButton
    private lateinit var ckBox:AppCompatCheckBox
    private lateinit var edID:AppCompatEditText
    private lateinit var edPassword:AppCompatEditText
    private lateinit var agreement: TextView

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
        agreement = bottomSheetView.findViewById<TextView>(R.id.user_agreement)

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

        agreement.setOnClickListener {
            val intent = Intent(context,UsersAgreementActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun handleLogin() {
        val ID = edID.text.toString().trim()
        val passWord = edPassword.text.toString().trim()

        if (ID.isEmpty()){
            showToast("手机号不能为空")
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

        // 添加保存登录状态的逻辑
        saveLoginState(true)  // 保存登录状态为 true
        showToast("登录成功！")
        val intent = Intent(context, ShowActivity::class.java)
        // 清空 Activity 栈，避免返回登录界面
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
        dismiss()

//        val intent = Intent(context,ShowActivity::class.java)
//        context.startActivity(intent)
//        dismiss()
    }

    // 保存登录状态到 SharedPreferences
    private fun saveLoginState(isLoggedIn: Boolean) {
        val sharedPref = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("isLoggedIn", isLoggedIn)
            apply()  // 异步提交
        }
    }

}