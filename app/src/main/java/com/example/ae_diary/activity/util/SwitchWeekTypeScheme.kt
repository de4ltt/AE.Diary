package com.example.ae_diary.activity.util

import android.content.Context
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.ae_diary.activity.MainActivity
import com.example.ae_diary.presentation.model.util.WeekType
import com.example.ae_diary.presentation.ui.theme.util.isEvenWeek
import com.example.ae_diary.presentation.ui.theme.util.switchWeekTypeScheme
import com.example.ae_diary.presentation.util.TimeFormatter.curTimeFlow
import com.example.ae_diary.presentation.util.TimeFormatter.dateFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal fun switchWeekTypeSchemeAccordingly(
    lifecycleScope: LifecycleCoroutineScope,
    semesterTime: Flow<Triple<String, String, WeekType>>,
    context: Context
): Job = lifecycleScope.launch {
    withContext(Dispatchers.IO) {
        curTimeFlow.collectLatest {
            semesterTime.collectLatest { time ->
                if (isEvenWeek() && curWeekType(
                        it.format(dateFormatter),
                        semesterTime = time
                    ) == WeekType.ODD
                ) {
                    switchWeekTypeScheme(context as MainActivity)
                }

                if (!isEvenWeek() && curWeekType(
                        it.format(dateFormatter),
                        semesterTime = time
                    ) == WeekType.EVEN
                ) {
                    switchWeekTypeScheme(context as MainActivity)
                }
            }
        }
    }
}