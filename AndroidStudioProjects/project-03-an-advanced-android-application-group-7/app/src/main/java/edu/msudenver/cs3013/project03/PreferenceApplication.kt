package edu.msudenver.cs3013.project03

import android.app.Application
import android.content.Context
/** Application that aids in data sharing, using
 * */
class PreferenceApplication : Application() {
    lateinit var preferenceWrapper: PreferenceWrapper
    lateinit var favoritesStore: FavoritesStore
    override fun onCreate() {
        super.onCreate()
        preferenceWrapper = PreferenceWrapper(getSharedPreferences("prefs", Context.MODE_PRIVATE))
        favoritesStore = FavoritesStore(this)
    }
}