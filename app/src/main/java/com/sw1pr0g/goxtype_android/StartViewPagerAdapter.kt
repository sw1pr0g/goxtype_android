package com.sw1pr0g.goxtype_android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class StartViewPagerAdapter(var context: Context) : PagerAdapter() {
    private var images = intArrayOf(
        R.drawable.typing_man,
        R.drawable.notification_man,
        R.drawable.ellipse1,
        R.drawable.ellipse2
    )
    private var heading = intArrayOf(
        R.string.start_heading_first_slider,
        R.string.start_heading_second_slider,
        R.string.start_heading_third_slider,
        R.string.start_heading_fourth_slider
    )
    private var description = intArrayOf(
        R.string.start_description_first_slider,
        R.string.start_description_second_slider,
        R.string.start_description_third_slider,
        R.string.start_description_fourth_slider
    )

    override fun getCount(): Int {
        return heading.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view === `object` as LinearLayout

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = layoutInflater.inflate(R.layout.slider_start_layout, container, false)

        val slideTitleImage = view.findViewById<View>(R.id.slider_start_image_view) as ImageView

        val slideHeading = view.findViewById<View>(R.id.slider_start_heading) as TextView

        val slideDescription = view.findViewById<View>(R.id.slider_start_description) as TextView

        slideTitleImage.setImageResource(images[position])
        slideHeading.setText(heading[position])
        slideDescription.setText(description[position])
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}