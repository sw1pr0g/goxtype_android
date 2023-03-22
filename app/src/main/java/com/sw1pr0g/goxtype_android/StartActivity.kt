package com.sw1pr0g.goxtype_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class StartActivity : AppCompatActivity() {

    private var imageList = mutableListOf<Int>(R.drawable.ic_login_hero)
    private var headingList = mutableListOf<Int>(R.drawable.ic_login_hero)
    private var descriptionList = mutableListOf<Int>(R.drawable.ic_login_hero)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_start)



    }
}