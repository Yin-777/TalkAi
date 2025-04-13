package com.example.talkai.ui.activity.Sheets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import com.example.talkai.R
import com.example.talkai.ui.activity.ProgressBar.ZDYProgressDialog
import com.example.talkai.ui.activity.ShowActivity
import com.example.talkai.utils.NetUtil
import com.example.talkai.utils.ResultObject
import com.google.android.material.bottomsheet.BottomSheetDialog


class IDLoginSheet(context: Context) : BottomSheetDialog(context) {

    private lateinit var edID: AppCompatEditText
    private lateinit var edPassword: AppCompatEditText
    private lateinit var btnLogin: AppCompatButton
    private lateinit var ckBox: AppCompatCheckBox
    private lateinit var agreement: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_login_id, null)
        setContentView(bottomSheetView)

        initViews(bottomSheetView)
        setupListeners()
    }

    private fun initViews(view: android.view.View) {
        edID = view.findViewById(R.id.ed_id)
        edPassword = view.findViewById(R.id.ed_password)
        btnLogin = view.findViewById(R.id.btn_login_id)
        ckBox = view.findViewById(R.id.ck_box)
        agreement = view.findViewById(R.id.user_agreement)
    }

    private fun setupListeners() {
        btnLogin.setOnClickListener {
            handleLogin()
        }

        ckBox.setOnCheckedChangeListener { _, isChecked ->
            btnLogin.isEnabled = isChecked
        }

        agreement.setOnClickListener {
            context.startActivity(Intent(context, UsersAgreementActivity::class.java))
        }
    }

    private fun handleLogin() {
        val username = edID.text.toString().trim()
        val password = edPassword.text.toString().trim()

        when {
            username.isEmpty() -> showToast("手机号不能为空")
            password.isEmpty() -> showToast("密码不能为空")
            password.length < 6 -> showToast("密码长度不能小于6位")
            !ckBox.isChecked -> showToast("请先同意用户协议")
            else -> proceedWithLogin(username, password)
        }
    }

    private fun proceedWithLogin(username: String, password: String) {
        val progressDialog = ZDYProgressDialog(context).apply { show() }

        NetUtil.login(username, password, object : NetUtil.NetCallback<ResultObject> {
            override fun onSuccess(result: ResultObject) {
                progressDialog.dismiss()
                if (result.code == 200) {
                    showToast("登录成功")
                    saveLoginState(true)
                    val intent = Intent(context, ShowActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                    dismiss()
                } else {
                    showToast("登录失败: ${result.msg}")
                }
            }

            override fun onFailure(t: Throwable) {
                progressDialog.dismiss()
                showToast("请求失败: ${t.message}")
            }
        })
    }


//    private fun handleLoginSuccess() {
//        showToast("登录成功")
//        saveLoginState(true)
//        val intent = Intent(context, ShowActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        context.startActivity(intent)
//        dismiss()
//    }

    private fun saveLoginState(isLoggedIn: Boolean) {
        val sharedPref = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("isLoggedIn", isLoggedIn)
            apply()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
