package com.example.talkai.ui.activity.Sheets

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import com.example.talkai.R
import com.example.talkai.ui.activity.ShowActivity
import com.example.talkai.utils.LambdaCodeLoginDTO
import com.example.talkai.utils.LoginResult
import com.example.talkai.utils.NetUtil
import com.example.talkai.utils.ResultLoginVO
import com.example.talkai.utils.ResultObject
import com.google.android.material.bottomsheet.BottomSheetDialog

class PhoneLoginSheet(context: Context):BottomSheetDialog(context) {

    private lateinit var etPhone: AppCompatEditText
    private lateinit var etCode: AppCompatEditText
    private lateinit var btnSent: AppCompatButton
    private lateinit var btnLogin: AppCompatButton
    private lateinit var ckBox:AppCompatCheckBox
    private lateinit var agreement: TextView

    private var verificationCode: String? = null
    private var phoneNumber: String = ""
    private var inputCode:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 设置布局
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_login_phone,null)
        setContentView(bottomSheetView)

        // 找到BottomSheetDialog中的按钮
        btnSent = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_sent)
        btnLogin = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_login_phone)
        etPhone = bottomSheetView.findViewById<AppCompatEditText>(R.id.et_phone_login)  // 需要给你的手机号EditText添加id
        etCode = bottomSheetView.findViewById<AppCompatEditText>(R.id.et_code_login)
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


    private fun handleLogin() {
        inputCode = etCode.text.toString().trim()
        if (inputCode.isEmpty()) {
            showToast("请输入验证码")
            return
        }

        if (!ckBox.isChecked) {
            showToast("请先同意相关条款")
            return
        }

        // 构造验证码校验请求
        val verifyRequest = NetUtil.VerifyCodeRequest(
            phone = phoneNumber,
            code = inputCode
        )

        // 先校验验证码是否正确
        NetUtil.verifyCode(verifyRequest, object : NetUtil.NetCallback<ResultObject> {
            override fun onSuccess(result: ResultObject) {
                if (result.code == 200) {
                    // 验证码正确，继续执行登录逻辑
                    performLogin()
                } else {
                    showToast("验证码错误: ${result.msg}")
                }
            }

            override fun onFailure(t: Throwable) {
                showToast("验证码校验失败: ${t.message}")
            }
        })
    }

    private fun performLogin() {
        // 构造登录请求
        val request = LambdaCodeLoginDTO(
            username = phoneNumber,
            code = inputCode
        )

        // 调用登录接口
        NetUtil.loginWithCode(request, object : NetUtil.NetCallback<ResultLoginVO> {
            override fun onSuccess(result: ResultLoginVO) {
                saveLoginState(result.data)
                showToast("登录成功")
                val intent = Intent(context, ShowActivity::class.java)
                context.startActivity(intent)
                dismiss()
            }

            override fun onFailure(t: Throwable) {
                showToast("登录失败: ${t.message}")
            }
        })
    }

    private fun saveLoginState(loginResult: LoginResult?) {
        // 保存token和用户信息到本地
        val sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("token", loginResult?.token ?: "")
            // 保存其他用户信息...
            apply()
        }
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
        // 简单的手机号验证逻辑（可根据需求调整）
        return phone.length == 11 && phone.startsWith("1")
    }

//    private fun generateRandomCode(): String {
//        // 生成6位随机数字验证码
//        return (100000..999999).random().toString()
//    }

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

//    private fun Login() {
//        // 这里添加你的注册逻辑
//        showToast("登录成功！")
//        dismiss()
//    }
}
