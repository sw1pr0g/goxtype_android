package com.sw1pr0g.goxtype_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.text.HtmlCompat
import androidx.viewpager.widget.ViewPager

class StartActivity : AppCompatActivity() {

    private var startViewPager: ViewPager? = null
    private var mDotLayout: LinearLayout? = null
    private var nextButton: Button? = null
    private var skipButton: Button? = null
    private lateinit var dots: Array<TextView?>
    private var startViewPagerAdapter: StartViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_start)

        nextButton = findViewById(R.id.next_button)
        skipButton = findViewById(R.id.skip_button)
        startViewPager = findViewById(R.id.start_view_pager)

        nextButton!!.setOnClickListener {
            if (getItem(0) < 3) startViewPager!!.setCurrentItem(getItem(1), true)
            else startMainActivity()
        }
        skipButton!!.setOnClickListener { startMainActivity() }

        mDotLayout = findViewById(R.id.indicator_layout)
        startViewPagerAdapter = StartViewPagerAdapter(this)
        startViewPager!!.adapter = startViewPagerAdapter
        setupIndicator(0)
        startViewPager!!.addOnPageChangeListener(viewListener)
    }

    fun setupIndicator(position: Int) {
        dots = arrayOfNulls(4)
        mDotLayout!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]?.text = HtmlCompat.fromHtml("&#8226", HtmlCompat.FROM_HTML_MODE_LEGACY)
            dots[i]!!.textSize = 35f
            dots[i]?.setTextColor(
                resources.getColor(
                    R.color.inactive,
                    applicationContext.theme
                )
            )
            mDotLayout!!.addView(dots[i])
        }
        dots[position]?.setTextColor(
            resources.getColor(
                R.color.active,
                applicationContext.theme
            )
        )
    }

    private var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }
        override fun onPageSelected(position: Int) { setupIndicator(position) }
        override fun onPageScrollStateChanged(state: Int) {}
    }

    private fun getItem(i: Int): Int {
        return startViewPager!!.currentItem + i
    }

    private fun startMainActivity() {
        val i = Intent(this@StartActivity, AuthActivity::class.java)
        startActivity(i)
        finish()
    }
}