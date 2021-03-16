package com.example.android.myapplication.commons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.android.myapplication.commons.uicomponents.ErrorDialog

abstract class BaseFragment: Fragment() {

    var errorDialog : ErrorDialog? = null

    abstract fun loadObservers()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        errorDialog?.dismiss()
    }


}