package com.example.talkai.ui.activity.Sheets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.talkai.R
import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivitySettingBinding
import com.example.talkai.utils.ToastUtil
import com.google.android.material.bottomsheet.BottomSheetDialog

class SettingActivity : BaseActivity<ActivitySettingBinding>() {
    override fun initViewBinding(): ActivitySettingBinding {
        return ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun initView() {
        val anotherLayout = LayoutInflater.from(this).inflate(R.layout.bottom_me_dialog, null)

        val linearLayoutName: LinearLayout = anotherLayout.findViewById(R.id.change_name)
        val linearLayoutCamera: LinearLayout = anotherLayout.findViewById(R.id.camera_img)
        val linearLayoutPhoto: LinearLayout = anotherLayout.findViewById(R.id.photo_img)
        val linearLayoutCancel: LinearLayout = anotherLayout.findViewById(R.id.cancel)
        val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(this)
        val bottomView: View = LayoutInflater.from(this).inflate(R.layout.bottom_me_dialog, null)
        bottomSheetDialog.setContentView(bottomView)
        binding.imgBack.setOnClickListener {
            this.finish()
        }
        //TODO:实现手机号码的展示，更改信息，用户id的展示，以及推出登录
//TODO:点击事件失效
        linearLayoutName.setOnClickListener {
            ToastUtil.showToast("1")
        }
        linearLayoutCamera.setOnClickListener {
            ToastUtil.showToast("2")
        }
        linearLayoutPhoto.setOnClickListener {
            ToastUtil.showToast("3")
        }
        linearLayoutCancel.setOnClickListener {
            ToastUtil.showToast("4")
        }
        binding.linearlayoutChange.setOnClickListener {
            bottomSheetDialog.show()
        }


    }
}