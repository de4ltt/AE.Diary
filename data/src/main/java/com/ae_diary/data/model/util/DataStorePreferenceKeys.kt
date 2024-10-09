package com.ae_diary.data.model.util

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

data object DataStorePreferenceKeys {

    val START_TIME: Preferences.Key<String> = stringPreferencesKey("start_time")
    val END_TIME: Preferences.Key<String> = stringPreferencesKey("end_time")
    val FIRST_WEEK_TYPE: Preferences.Key<String> = stringPreferencesKey("first_week_type")
    val HOLIDAYS: Preferences.Key<String> = stringPreferencesKey("holidays")
}
