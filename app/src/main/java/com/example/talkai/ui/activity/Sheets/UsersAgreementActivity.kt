package com.example.talkai.ui.activity.Sheets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.talkai.R
import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivityUserBinding
import com.example.talkai.databinding.ActivityUsersAgreementBinding

class UsersAgreementActivity : BaseActivity<ActivityUsersAgreementBinding>() {
    override fun initViewBinding(): ActivityUsersAgreementBinding {
        return ActivityUsersAgreementBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.imgBack.setOnClickListener {
            this.finish()
        }
    }
}