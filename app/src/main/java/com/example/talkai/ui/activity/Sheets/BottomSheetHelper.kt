//package com.example.talkai.ui.activity.Sheets
//
//import android.content.Context
//import android.view.View
//import android.view.ViewGroup
//import com.example.talkai.R
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.google.android.material.bottomsheet.BottomSheetDialog
//
//class BottomSheetHelper(
//    private val context: Context,
//    private val layoutResId: Int
//) {
//    private var bottomSheetDialog: BottomSheetDialog? = null
//    private var dialogView: View? = null
//
//    init {
//        initializeDialog()
//    }
//
//    private fun initializeDialog() {
//        // 使用带主题的构造方法
//        bottomSheetDialog = BottomSheetDialog(context, R.style.CustomBottomSheet).apply {
//            // 正确inflate方式：通过setContentView传入资源ID
//            setContentView(layoutResId)
//
//            // 获取根视图（关键步骤）
//            dialogView = findViewById<ViewGroup>(android.R.id.content)?.getChildAt(0)
//
//            // 配置默认行为
//            behavior.apply {
//                skipCollapsed = true
//                state = BottomSheetBehavior.STATE_EXPANDED
//            }
//        }
//    }
//
//    fun show() {
//        bottomSheetDialog?.show()
//    }
//
//    fun dismiss() {
//        bottomSheetDialog?.dismiss()
//    }
//
//    fun <T : View> getView(viewId: Int): T? {
//        return dialogView?.findViewById(viewId)
//    }
//
//    // 配置底部表单行为
//    fun configureBehavior(block: (BottomSheetBehavior<View>.() -> Unit)) {
//        bottomSheetDialog?.dialog?.window?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
//            ?.let {
//                BottomSheetBehavior.from(it).apply(block)
//            }
//    }
//}