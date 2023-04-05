package com.sw1pr0g.goxtype_android.ui.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sw1pr0g.goxtype_android.databinding.ActivityStartBinding
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.auth.AuthActivity

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityStartBinding.inflate(layoutInflater)
        val component = Component(this)
        setContentView(binding.root)

        binding.skipButton.setOnClickListener { component.newActivity(AuthActivity::class.java) }

        binding.startSliderViewPager2.adapter = StartSliderAdapter(StartData().loadSlides())
        binding.startSliderViewPager2.currentItem

        binding.circleIndicator.setViewPager(binding.startSliderViewPager2)

        binding.nextButton.setOnClickListener {
            if (binding.startSliderViewPager2.currentItem == binding.circleIndicator.childCount-1)
                component.newActivity(AuthActivity::class.java)
            else binding.startSliderViewPager2.currentItem++
        }
    }
}