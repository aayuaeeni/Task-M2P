package com.raju.m2p.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.raju.m2p.databinding.ActivitySplashBinding
import com.raju.m2p.presentation.base.BaseActivity
import com.raju.m2p.presentation.playlist.PlaylistActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSystemUI()
        openApp()
    }

    private fun openApp() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, PlaylistActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}