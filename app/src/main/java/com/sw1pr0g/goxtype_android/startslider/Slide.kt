package com.sw1pr0g.goxtype_android.startslider

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Slide(
    @DrawableRes val imageResourceId: Int,
    @StringRes val headingResourceId: Int,
    @StringRes val descriptionResourceId: Int
)