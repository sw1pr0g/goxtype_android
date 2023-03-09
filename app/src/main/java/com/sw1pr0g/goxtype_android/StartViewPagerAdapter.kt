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
    var images = intArrayOf(
        R.drawable.typing_man,
        R.drawable.notification_man,
        R.drawable.ellipse1,
        R.drawable.ellipse2
    )
    var heading = intArrayOf(
        R.string.start_heading_first_slider,
        R.string.start_heading_second_slider,
        R.string.start_heading_third_slider,
        R.string.start_heading_fourth_slider
    )
    var description = intArrayOf(
        R.string.start_description_first_slider,
        R.string.start_description_second_slider,
        R.string.start_description_third_slider,
        R.string.start_description_fourth_slider
    )

    override fun getCount(): Int {
        return heading.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = layoutInflater.inflate(R.layout.slider_layout, container, false)

        val slidetitleimage = view.findViewById<View>(R.id.titleImage) as ImageView

        val slideHeading = view.findViewById<View>(R.id.texttitle) as TextView

        val slideDescription = view.findViewById<View>(R.id.textdescription) as TextView

        slidetitleimage.setImageResource(images[position])
        slideHeading.setText(heading[position])
        slideDescription.setText(description[position])
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}