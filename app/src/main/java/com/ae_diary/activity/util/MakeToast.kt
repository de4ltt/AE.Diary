package com.ae_diary.activity.util

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.annotation.StringRes

fun makeToast(context: Context, @StringRes resId: Int) = with(context) {
    Toast.makeText(this, resId, LENGTH_SHORT).show()
}