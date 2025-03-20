import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
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


}