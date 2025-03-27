package com.example.talkai.ui.activity


import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.talkai.R
import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivityAdviceMainBinding


class AdviceMainActivity : BaseActivity<ActivityAdviceMainBinding>() {
    override fun initViewBinding(): ActivityAdviceMainBinding {
        return ActivityAdviceMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        //实现意见反馈界面的逻辑
        binding.imgBack.setOnClickListener {
            this.finish()
        }
        binding.button.setOnClickListener {
            //TODO:进行网络请求，传入反馈数据

            showToast()
            this.finish()    //自定义toast,先进行linearlayout布局,在view导入toast里面
        }
    }

    private fun showToast() {
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.custom_toast, null)

        val text = layout.findViewById<TextView>(R.id.text)
        text.text = "提交成功，感谢你的反馈"

        val toast = Toast(applicationContext)
        toast.setView(layout) // 设置自定义布局

        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }
}