package com.sw1pr0g.goxtype_android.startslider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.PagerSnapHelper
import com.sw1pr0g.goxtype_android.AuthActivity
import com.sw1pr0g.goxtype_android.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.skipButton.setOnClickListener { showAuthActivity() }

        val recyclerViewAdapter = StartSliderAdapter(StartData().loadSlides())

        binding.startSliderRecyclerView.adapter = recyclerViewAdapter
        binding.startSliderRecyclerView.setHasFixedSize(true)

        val pagerSnapHelper = PagerSnapHelper()

        pagerSnapHelper.attachToRecyclerView(binding.startSliderRecyclerView)
        binding.circleIndicator.attachToRecyclerView(binding.startSliderRecyclerView, pagerSnapHelper)
    }

    fun showAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}