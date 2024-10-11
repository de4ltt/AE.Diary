package com.ae_diary.presentation.util

import android.content.Context
import com.ae_diary.R
import com.ae_diary.activity.util.makeToast
import com.ae_diary.presentation.ui.screen.destinations.CertificatesScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.DiaryScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.DirectionDestination
import com.ae_diary.presentation.ui.screen.destinations.TimetableScreenDestination

private val checkDestinations = listOf(
    CertificatesScreenDestination,
    DiaryScreenDestination,
    CertificatesScreenDestination,
    TimetableScreenDestination
)

internal fun checkDestinationForAvailability(
    destination: DirectionDestination,
    semesterState: Boolean,
    context: Context
): Boolean =
    if (semesterState || !checkDestinations.contains(destination))
        true
    else {
        makeToast(context, R.string.semester_time_unset)
        false
    }