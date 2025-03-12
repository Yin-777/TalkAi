import android.content.Context
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.talkai.R
import com.example.talkai.ui.activity.Sheets.IDLoginSheet
import com.example.talkai.ui.activity.Sheets.PhoneLoginSheet
import com.google.android.material.bottomsheet.BottomSheetDialog

class LoginSheet(context: Context) : BottomSheetDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 设置布局
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_login, null)
        setContentView(bottomSheetView)

        // 找到 BottomSheetDialog 中的按钮
        val btnEmail = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_email)
        val btnPhone = bottomSheetView.findViewById<AppCompatButton>(R.id.btn_phone)

        // 设置按钮点击事件
        btnEmail.setOnClickListener {
//            handleIDLogin()
            val loginBottomSheetDialog = IDLoginSheet(context)
            loginBottomSheetDialog.show()
            dismiss() // 关闭 BottomSheetDialog
        }

        btnPhone.setOnClickListener {
//            handlePhoneLogin()
            val loginBottomSheetDialog = PhoneLoginSheet(context)
            loginBottomSheetDialog.show()
            dismiss() // 关闭 BottomSheetDialog
        }
    }

//    private fun handleEmailLogin() {
//        // 处理邮箱登录逻辑
//        val intent = Intent(context, EmailLoginActivity::class.java)
//        intent.putExtra("email", "user@example.com") // 传递数据
//        context.startActivity(intent)
//    }
//
//    private fun handlePhoneLogin() {
//        // 处理手机登录逻辑
//        val intent = Intent(context, PhoneLoginActivity::class.java)
//        intent.putExtra("phone", "1234567890") // 传递数据
//        context.startActivity(intent)
//    }
}