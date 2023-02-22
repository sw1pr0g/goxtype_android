package com.sw1pr0g.goxtype_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager

class StartActivity : AppCompatActivity() {

    var mSLideViewPager: ViewPager? = null
    var mDotLayout: LinearLayout? = null
    var backbtn: Button? = null
    var nextbtn: Button? = null
    var skipbtn: Button? = null
    lateinit var dots: Array<TextView?>
    var startViewPagerAdapter: StartViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        backbtn = findViewById(R.id.backbtn)
        nextbtn = findViewById(R.id.nextbtn)
        skipbtn = findViewById(R.id.skipButton)
        backbtn!!.setOnClickListener {
            if (getitem(0) > 0) {
                mSLideViewPager!!.setCurrentItem(getitem(-1), true)
            }
        }
        nextbtn!!.setOnClickListener {
            if (getitem(0) < 3) mSLideViewPager!!.setCurrentItem(getitem(1), true) else {
                val i = Intent(this@StartActivity, AuthActivity::class.java)
                startActivity(i)
                finish()
            }
        }
        skipbtn!!.setOnClickListener {
            val i = Intent(this@StartActivity, AuthActivity::class.java)
            startActivity(i)
            finish()
        }
        mSLideViewPager = findViewById(R.id.StartViewPager) as ViewPager?
        mDotLayout = findViewById(R.id.indicator_layout) as LinearLayout?
        startViewPagerAdapter = StartViewPagerAdapter(this)
        mSLideViewPager!!.adapter = startViewPagerAdapter
        setUpindicator(0)
        mSLideViewPager!!.addOnPageChangeListener(viewListener)
    }

    fun setUpindicator(position: Int) {
        dots = arrayOfNulls(4)
        mDotLayout!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]?.setText(Html.fromHtml("&#8226"))
            dots[i]!!.textSize = 35f
            dots[i]?.setTextColor(
                getResources().getColor(
                    R.color.inactive,
                    getApplicationContext().getTheme()
                )
            )
            mDotLayout!!.addView(dots[i])
        }
        dots[position]?.setTextColor(
            getResources().getColor(
                R.color.active,
                getApplicationContext().getTheme()
            )
        )
    }

    var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            setUpindicator(position)
            if (position > 0) {
                backbtn!!.visibility = View.VISIBLE
            } else {
                backbtn!!.visibility = View.INVISIBLE
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private fun getitem(i: Int): Int {
        return mSLideViewPager!!.currentItem + i
    }
}