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
import com.example.talkai.utils.RegisterDTO
import com.example.talkai.utils.ResultObject
import com.google.android.material.bottomsheet.BottomSheetDialog

class RegisterSheet(context: Context) : BottomSheetDialog(context) {

    private lateinit var etPhone: AppCompatEditText
    private lateinit var etPassword: AppCompatEditText
    private lateinit var btnRegister: AppCompatButton
    private lateinit var ckBox: AppCompatCheckBox
    private lateinit var agreement: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_register, null)
        setContentView(bottomSheetView)

        initViews(bottomSheetView)
        setupListeners()
    }

    private fun initViews(rootView: android.view.View) {
        etPhone = rootView.findViewById(R.id.et_name)
        etPassword = rootView.findViewById(R.id.et_password)
        btnRegister = rootView.findViewById(R.id.btn_register)
        ckBox = rootView.findViewById(R.id.ck_box)
        agreement = rootView.findViewById(R.id.user_agreement)
    }

    private fun setupListeners() {
        btnRegister.setOnClickListener {
            handleRegistration()
        }

        ckBox.setOnCheckedChangeListener { _, isChecked ->
            btnRegister.isEnabled = isChecked
        }

        agreement.setOnClickListener {
            navigateToUserAgreement()
        }
    }

    private fun handleRegistration() {
        val username = etPhone.text.toString().trim()
        val password = etPassword.text.toString().trim()

        when {
            username.isEmpty() -> showToast("请输入用户名")
            password.isEmpty() -> showToast("请输入密码")
            password.length < 6 -> showToast("密码必须至少6位")
            !ckBox.isChecked -> showToast("请同意用户协议")
            else -> proceedWithRegistration(username, password)
        }
    }

    private fun proceedWithRegistration(username: String, password: String) {
        val progressDialog = ZDYProgressDialog(context).apply { show() }

        val request = RegisterDTO(username = username, password = password)


        NetUtil.register(request, object : NetUtil.NetCallback<ResultObject> {
            override fun onSuccess(result: ResultObject) {
                if (result.code == 201) {
                    // 注册成功
                    progressDialog.dismiss()
                    showToast("注册成功")
                } else if(result.code == 400){
                    // 注册失败逻辑
                    progressDialog.dismiss()
                    showToast("注册失败: ${result.msg}")
                }
            }

            override fun onFailure(t: Throwable) {
                progressDialog.dismiss()
                showToast("请求失败: ${t.message}")
            }
        })
    }

//    private fun handleRegistrationSuccess() {
//        showToast("注册成功")
//        context.startActivity(Intent(context, ShowActivity::class.java))
//        dismiss()
//    }

    private fun navigateToUserAgreement() {
        val intent = Intent(context, UsersAgreementActivity::class.java)
        context.startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
