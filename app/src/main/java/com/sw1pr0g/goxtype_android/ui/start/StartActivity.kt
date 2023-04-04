package com.sw1pr0g.goxtype_android.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sw1pr0g.goxtype_android.databinding.ActivityStartBinding
import com.sw1pr0g.goxtype_android.ui.auth.AuthActivity

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.skipButton.setOnClickListener { showAuthActivity() }

        binding.startSliderViewPager2.adapter = StartSliderAdapter(StartData().loadSlides())
        binding.startSliderViewPager2.currentItem

        binding.circleIndicator.setViewPager(binding.startSliderViewPager2)

        binding.nextButton.setOnClickListener {
            if (binding.startSliderViewPager2.currentItem == binding.circleIndicator.childCount-1) showAuthActivity()
            else binding.startSliderViewPager2.currentItem++
        }
    }

    private fun showAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}