package com.sw1pr0g.goxtype_android.ui.start

import com.sw1pr0g.goxtype_android.R

class StartData {

    fun loadSlides(): List<Slide> {
        return listOf(
            Slide(R.drawable.typing_man, R.string.start_heading_first_slider, R.string.start_description_first_slider),
            Slide(R.drawable.typing_man, R.string.start_heading_second_slider, R.string.start_description_second_slider),
            Slide(R.drawable.typing_man, R.string.start_heading_third_slider, R.string.start_description_third_slider),
            Slide(R.drawable.typing_man, R.string.start_heading_fourth_slider, R.string.start_description_fourth_slider)
        )
    }

}