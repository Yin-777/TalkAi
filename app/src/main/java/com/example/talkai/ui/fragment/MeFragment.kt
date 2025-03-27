package com.example.talkai.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.talkai.R
import com.example.talkai.base.BaseFragment
import com.example.talkai.databinding.FragmentMeBinding
import com.example.talkai.ui.activity.AboutActivity
import com.example.talkai.ui.activity.AdviceMainActivity
import com.example.talkai.ui.activity.HelpMainActivity
import com.example.talkai.ui.activity.Sheets.SettingActivity
import com.example.talkai.ui.activity.VipMainActivity
import com.example.talkai.utils.LogUtils
import com.example.talkai.utils.ToastUtil


class MeFragment : BaseFragment<FragmentMeBinding>(FragmentMeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        //将头像变圆
        imgHead()
        //设置我的界面点击事件，包含了五个按键
        function()
    }

    private fun function() {
        //需要用每个linearlayout布局的点击事件
        //心得：布局点击事件要在xml里设置 android:clickable="true"，如果布局有控件要控件点击取消
        binding.vipLinearlayout.setOnClickListener {
            //实现vip点击功能
            val intent = Intent(requireActivity(), VipMainActivity::class.java)
            startActivity(intent)
        }
        binding.helpLinearlayout.setOnClickListener {
            //实现帮助中心功能
            val intent = Intent(requireActivity(), HelpMainActivity::class.java)
            startActivity(intent)
        }
        binding.connectLinearlayout.setOnClickListener {
            //实现意见反馈功能
            val intent = Intent(requireActivity(), AdviceMainActivity::class.java)
            startActivity(intent)
        }
        binding.aboutLinearlayout.setOnClickListener {
            //实现关于我们功能
            val intent = Intent(requireActivity(), AboutActivity::class.java)
            startActivity(intent)
        }
        binding.settingLinearlayout.setOnClickListener {
            //实现设置功能
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }

    }

    private fun imgHead() {
        Glide.with(this).load(R.drawable.img).apply(RequestOptions.circleCropTransform())
            .into(binding.imageViewHead)
    }

}