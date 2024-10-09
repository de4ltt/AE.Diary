package com.ae_diary.activity.util

import android.app.Activity
import android.view.WindowManager

internal fun Activity.setDisplayCutoutMode() {
    window.attributes.layoutInDisplayCutoutMode =
        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
}