package com.sw1pr0g.goxtype_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.sw1pr0g.goxtype_android.databinding.ActivityStartBinding
import me.relex.circleindicator.CircleIndicator3

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    private var imageList = mutableListOf<Int>()
    private var headingList = mutableListOf<Int>()
    private var descriptionList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.skipButton.setOnClickListener { showAuthActivity() }

        binding.startViewPager2.adapter = StartViewPagerAdapter2(imageList, headingList, descriptionList)
        binding.startViewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        addToList()

        val indicator = findViewById<CircleIndicator3>(R.id.circle_indicator)
        indicator.setViewPager(binding.startViewPager2)

        binding.nextButton.setOnClickListener {
            if (binding.startViewPager2.currentItem == indicator.childCount-1) showAuthActivity()
            else binding.startViewPager2.currentItem += 1
        }
    }

    private fun addToList() {
        //1
        imageList.add(R.drawable.typing_man)
        headingList.add(R.string.start_heading_first_slider)
        descriptionList.add(R.string.start_description_first_slider)

        //2
        imageList.add(R.drawable.logo) // Previous -> typing_man
        headingList.add(R.string.start_heading_second_slider)
        descriptionList.add(R.string.start_description_second_slider)

        //3
        imageList.add(R.drawable.typing_man)
        headingList.add(R.string.start_heading_third_slider)
        descriptionList.add(R.string.start_description_third_slider)

        //4
        imageList.add(R.drawable.typing_man)
        headingList.add(R.string.start_heading_fourth_slider)
        descriptionList.add(R.string.start_description_fourth_slider)
    }

    private fun showAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}