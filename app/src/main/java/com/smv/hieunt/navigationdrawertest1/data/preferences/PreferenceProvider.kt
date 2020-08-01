package com.smv.hieunt.navigationdrawertest1.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.smv.hieunt.navigationdrawertest1.utils.Common


class PreferenceProvider(
context: Context
) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun savelastSavedAt(saveAt: String){
        preference.edit().putString(
            Common.KEY_SAVED_AT,
            saveAt
        ).apply()
    }

    fun getLastSavedAt(): String? {
        return preference.getString(Common.KEY_SAVED_AT, null)
    }
}