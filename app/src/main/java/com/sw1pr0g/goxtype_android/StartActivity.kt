package com.sw1pr0g.goxtype_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3

class StartActivity : AppCompatActivity() {

    private lateinit var skipButton: Button
    private lateinit var viewPager2: ViewPager2
    private lateinit var nextButton: Button

    private var imageList = mutableListOf<Int>()
    private var headingList = mutableListOf<Int>()
    private var descriptionList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_start)

        skipButton = findViewById(R.id.skip_button)
        viewPager2 = findViewById(R.id.start_view_pager2)
        nextButton = findViewById(R.id.next_button)

        skipButton.setOnClickListener { showAuthActivity() }

        viewPager2.adapter = StartViewPagerAdapter2(imageList, headingList, descriptionList)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        addToList()

        val indicator = findViewById<CircleIndicator3>(R.id.circle_indicator)
        indicator.setViewPager(viewPager2)

        nextButton.setOnClickListener {
            if (viewPager2.currentItem == indicator.childCount-1) showAuthActivity()
            else viewPager2.currentItem += 1
        }
    }

    private fun addToList() {
        //1
        imageList.add(R.drawable.typing_man)
        headingList.add(R.string.start_heading_first_slider)
        descriptionList.add(R.string.start_description_first_slider)

        //2
        imageList.add(R.drawable.typing_man)
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