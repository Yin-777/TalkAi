package com.example.talkai.ui.activity.Sheets

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.talkai.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class PhoneLoginSheet(context: Context):BottomSheetDialog(context) {

    private lateinit var etPhone: AppCompatEditText
    private lateinit var etCode: AppCompatEditText
    private lateinit var btnSent: AppCompatButton
    private lateinit var btnLogin: AppCompatButton

    private var verificationCode: String? = null
    private var phoneNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 使其跟随系统主题颜色
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        // 设置布局
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_login_phone,null)
        setContentView(bottomSheetView)

        // 找到BottomSheetDialog中的按钮
        btnSent = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_sent)
        btnLogin = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_login_phone)
        etPhone = bottomSheetView.findViewById<AppCompatEditText>(R.id.et_phone_login)  // 需要给你的手机号EditText添加id
        etCode = bottomSheetView.findViewById<AppCompatEditText>(R.id.et_code_login)

        setupButtons()
    }
    private fun setupButtons() {
        // 发送验证码按钮
        btnSent.setOnClickListener {
            handleVerificationCodeSending()
        }

        // 注册按钮
        btnLogin.setOnClickListener {
            handleRegistration()
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

        // 生成并发送验证码（这里模拟生成）
        verificationCode = generateRandomCode()
        showToast("验证码已发送至：$phoneNumber")

        // 启动倒计时
        startCountDownTimer()
    }
    private fun handleRegistration() {
        // 获取输入框的验证码信息
        val inputCode = etCode.text.toString().trim()

        if (inputCode.isEmpty()) {
            showToast("请输入验证码")
            return
        }

        if (inputCode != verificationCode) {
            showToast("验证码不正确")
            return
        }

        // 验证通过，执行注册逻辑
        register()
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
        // 简单的手机号验证逻辑（可根据需求调整）
        return phone.length == 11 && phone.startsWith("1")
    }

    private fun generateRandomCode(): String {
        // 生成6位随机数字验证码
        return (100000..999999).random().toString()
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

    private fun register() {
        // 这里添加你的注册逻辑
        showToast("注册成功！")
        dismiss()
    }
}
