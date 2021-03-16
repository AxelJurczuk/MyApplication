package com.example.android.myapplication.splash_activity.ui

import android.os.Bundle
import com.example.android.myapplication.commons.BaseActivity
import com.example.android.myapplication.commons.Constants
import com.example.android.myapplication.databinding.ActivitySplashBinding
import com.example.android.myapplication.home_activity.HomeActivity
import org.jetbrains.anko.startActivity

class SplashActivity:BaseActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        object:Thread(){
            override fun run() {
                try {
                    sleep(Constants.LOADING_SLEEP)
                }
                catch (ie:InterruptedException){
                    ie.printStackTrace()
                }
                finally {
                    startActivity<HomeActivity>()
                    finish()
                }
            }
        }.start()
    }
}