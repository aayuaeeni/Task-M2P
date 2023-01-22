package com.raju.m2p.application

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

@HiltAndroidApp
class M2PApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initCalligraphyViewPump()
        setThreadPolicy()
    }

    private fun initCalligraphyViewPump() {

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/OpenSans-Regular.ttf").build()
                    )
                )
                .build()
        )

    }

    private fun setThreadPolicy() {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }
}