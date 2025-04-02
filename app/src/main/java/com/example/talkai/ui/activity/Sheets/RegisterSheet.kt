package com.example.talkai.ui.activity.Sheets

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
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

class RegisterSheet(context: Context):BottomSheetDialog(context) {

    private lateinit var etPhone: AppCompatEditText
    private lateinit var etCode: AppCompatEditText
    private lateinit var btnSent: AppCompatButton
    private lateinit var btnRegister: AppCompatButton
    private lateinit var etName: AppCompatEditText
    private lateinit var etPassword: AppCompatEditText
    private lateinit var ckBox: AppCompatCheckBox
    private lateinit var agreement: TextView

    private var verificationCode: String? = null
    private var phoneNumber: String = ""
    private var inputCode:String = ""
    private var inputPassword:String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 设置布局
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_register,null)
        setContentView(bottomSheetView)

        // 找到BottomSheetDialog中的按钮
        btnSent = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_sent)
        btnRegister = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_register)
        etPhone = bottomSheetView.findViewById<AppCompatEditText>(R.id.et_phone)  // 需要给你的手机号EditText添加id
        etCode = bottomSheetView.findViewById<AppCompatEditText>(R.id.et_code)    // 需要给你的验证码EditText添加id
        etPassword = bottomSheetView.findViewById<AppCompatEditText>(R.id.et_password)
        ckBox = bottomSheetView.findViewById<AppCompatCheckBox>(R.id.ck_box)
        agreement = bottomSheetView.findViewById<TextView>(R.id.user_agreement)
        setupButtons()

    }

    private fun setupButtons() {
        // 发送验证码按钮
        btnSent.setOnClickListener {
            handleVerificationCodeSending()
        }

        // 注册按钮
        btnRegister.setOnClickListener {
            handleRegistration()
        }

        // 监听复选框状态变化，动态更新按钮状态
        ckBox.setOnCheckedChangeListener { _, isChecked ->
            btnRegister.isEnabled = isChecked
        }

        agreement.setOnClickListener {
            val intent = Intent(context,UsersAgreementActivity::class.java)
            context.startActivity(intent)
        }
    }
    private fun handleVerificationCodeSending() {
        phoneNumber = etPhone.text.toString().trim()

        if (phoneNumber.isEmpty()) {
            showToast("请输入手机号码")
            return
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            showToast("手机号码格式不正确")
            return
        }

        // 调用发送验证码接口
        NetUtil.sendCode(phoneNumber, object : NetUtil.NetCallback<ResultObject> {
            override fun onSuccess(result: ResultObject) {
                // 启动倒计时
                startCountDownTimer()
                showToast("验证码发送成功")
            }

            override fun onFailure(t: Throwable) {
                showToast("验证码发送失败: ${t.message}")
            }
        })

    }
    private fun handleRegistration() {
        inputCode = etCode.text.toString().trim()
        inputPassword = etPassword.text.toString().trim()
        phoneNumber = etPhone.text.toString().trim()

        // 输入验证（原有逻辑不变）
        if (phoneNumber.isEmpty()) {
            showToast("请输入手机号码")
            return
        }
        if (!isValidPhoneNumber(phoneNumber)) {
            showToast("手机号码格式不正确")
            return
        }
        if (inputCode.isEmpty()) {
            showToast("请输入验证码")
            return
        }
        if (inputPassword.isEmpty()) {
            showToast("请设置密码")
            return
        }
        if (inputPassword.length < 6) {
            showToast("密码必须大于六位")
            return
        }
        if (!ckBox.isChecked) {
            showToast("请同意相关条款")
            return
        }

        // 显示加载对话框
        val progressDialog = ZDYProgressDialog(context)
        progressDialog.show()

        // 1. 先校验验证码
        val verifyRequest = NetUtil.VerifyCodeRequest(
            phone = phoneNumber,
            code = inputCode
        )

        NetUtil.verifyCode(verifyRequest, object : NetUtil.NetCallback<ResultObject> {
            override fun onSuccess(result: ResultObject) {
                if (result.code == 200) {
                    // 2. 验证码正确，继续注册
                    performRegistration(progressDialog)
                } else {
                    progressDialog.dismiss()
                    showToast("验证码错误: ${result.msg}")
                }
            }

            override fun onFailure(t: Throwable) {
                progressDialog.dismiss()
                showToast("验证码校验失败: ${t.message}")
            }
        })
    }

    // 拆分注册逻辑到独立方法
    private fun performRegistration(progressDialog: ZDYProgressDialog) {
        val request = RegisterDTO(
            username = phoneNumber,
            code = inputCode,
            password = inputPassword
        )

        NetUtil.register(request, object : NetUtil.NetCallback<ResultObject> {
            override fun onSuccess(result: ResultObject) {
                progressDialog.dismiss()
                showToast("注册成功")
                val intent = Intent(context, ShowActivity::class.java)
                context.startActivity(intent)
                dismiss()
            }

            override fun onFailure(t: Throwable) {
                progressDialog.dismiss()
                showToast("注册失败: ${t.message}")
            }
        })
    }


    private fun isValidPhoneNumber(phone: String): Boolean {
        // 简单的手机号验证逻辑（可根据需求调整）
        return phone.length == 11 && phone.startsWith("1")
    }



    @SuppressLint("SetTextI18n")
    private fun startCountDownTimer() {
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                btnSent.text = "${millisUntilFinished / 1000}秒后重发"
                btnSent.isEnabled = false
            }

            override fun onFinish() {
                btnSent.text = "发送验证码"
                btnSent.isEnabled = true
            }
        }.start()
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}