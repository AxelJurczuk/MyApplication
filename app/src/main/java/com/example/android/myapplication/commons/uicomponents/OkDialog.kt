package com.example.android.myapplication.commons.uicomponents

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.android.myapplication.databinding.DialogErrorBinding
import com.example.android.myapplication.databinding.DialogOkBinding

class OkDialog (context: Context,
                val mTitle: String,
                val mBody: String,
                val mButton: String?,
                val mListener: View.OnClickListener?
): Dialog(context){

    private lateinit var binding: DialogOkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogOkBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvDialogOkTitle.text = mTitle
        binding.tvDialogOkBody.text = mBody
        if (mButton == null && mListener == null) {
            binding.dialogOkButton.visibility = View.GONE
        } else {
            binding.dialogOkButton.text = mButton
            binding.dialogOkButton.setOnClickListener(mListener)
        }
    }
    override fun show() {
        super.show()
        val window = window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    fun setTitle(title : String){
        binding.dialogOkButton.text = title
    }
}