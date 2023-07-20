package msudenver.edu.sharepreferences

import android.app.Application
import android.content.Context


class PreferenceApplication : Application() {
    lateinit var preferenceWrapper: PreferenceWrapper
    override fun onCreate() {
        super.onCreate()
        preferenceWrapper = PreferenceWrapper(getSharedPreferences("prefs", Context.MODE_PRIVATE))
    }
}